package com.atom.statistics.service;

import com.atom.statistics.db.DatabaseConnector;
import com.atom.statistics.db.DatabaseConnectorFactory;
import com.atom.statistics.entity.BinaryLogsStatistics;
import com.atom.statistics.entity.DataSourceConfig;
import com.atom.statistics.entity.DatabaseSizeStatistics;
import com.atom.statistics.entity.InformationSchemaFilesStatistics;
import com.atom.statistics.mapper.BinaryLogsStatisticsMapper;
import com.atom.statistics.mapper.DataSourceConfigMapper;
import com.atom.statistics.mapper.DatabaseSizeStatisticsMapper;
import com.atom.statistics.mapper.InformationSchemaFilesStatisticsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Atom
 */
@Service
public class DatabaseStorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseStorageService.class);

    @Resource
    private DataSourceConfigMapper dataSourceConfigMapper;

    @Resource
    private DatabaseSizeStatisticsMapper databaseSizeStatisticsMapper;

    @Resource
    private BinaryLogsStatisticsMapper binaryLogsStatisticsMapper;

    @Resource
    private InformationSchemaFilesStatisticsMapper informationSchemaFilesStatisticsMapper;

    public void queryDatabaseStorage() {
        LOGGER.info("Starting database storage query...");

        List<DataSourceConfig> allDataSourceConfigs = dataSourceConfigMapper.getAllDataSourceConfigs();
        for (DataSourceConfig config : allDataSourceConfigs) {
            LOGGER.info("Processing data source: {}, {}, {}", config.getDatabaseType(), config.getHost(), config.getPort());
            processDataSource(config);
        }

        LOGGER.info("Database storage query completed.");
    }

    private void processDataSource(DataSourceConfig config) {
        String databaseType = config.getDatabaseType();
        String databaseUrl = generateDatabaseUrl(config);
        DatabaseConnector connector = DatabaseConnectorFactory.createConnector(databaseType);

        try {
            LOGGER.info("Connecting to data source: {}, {}, {}", config.getDatabaseType(), config.getHost(), config.getPort());
            connector.connect(databaseUrl, config.getUsername(), config.getPassword());

            List<DatabaseSizeStatistics> databaseSizeStatistics = connector.executeQueryAndGetStorageUsage();

            fillStatisticsInfoExtendField(databaseSizeStatistics, config);
            saveStorageUsageInfo(databaseSizeStatistics);
        } catch (Exception e) {
            LOGGER.error("Error processing data source: {}, {}, {}: {}", config.getDatabaseType(), config.getHost(), config.getPort(), e.getMessage(), e);
        } finally {
            connector.disconnect();
            LOGGER.info("Disconnected from data source: {}, {}, {}", config.getDatabaseType(), config.getHost(), config.getPort());
        }
    }

    private void fillStatisticsInfoExtendField(List<DatabaseSizeStatistics> databaseSizeStatistics, DataSourceConfig config) {
        String databaseType = config.getDatabaseType();
        String host = config.getHost();
        int port = config.getPort();

        LocalDateTime currentTime = LocalDateTime.now();

        databaseSizeStatistics.forEach(databaseSizeStatistic -> {
            databaseSizeStatistic.getBinaryLogsStatistics().forEach(binaryLogsStatistics -> {
                binaryLogsStatistics.setDatabaseHost(host);
                binaryLogsStatistics.setDatabasePort(port);
                binaryLogsStatistics.setCollectTime(currentTime);
                binaryLogsStatistics.setCreateTime(currentTime);
                binaryLogsStatistics.setUpdateTime(currentTime);
            });
            databaseSizeStatistic.getInformationSchemaFilesStatistics().forEach(informationSchemaFilesStatistics -> {
                informationSchemaFilesStatistics.setDatabaseHost(host);
                informationSchemaFilesStatistics.setDatabasePort(port);
                informationSchemaFilesStatistics.setDatabaseType(databaseType);
                informationSchemaFilesStatistics.setCollectTime(currentTime);
                informationSchemaFilesStatistics.setCreateTime(currentTime);
                informationSchemaFilesStatistics.setUpdateTime(currentTime);
            });
        });
    }

    private void saveStorageUsageInfo(List<DatabaseSizeStatistics> databaseSizeStatistics) {
        for (DatabaseSizeStatistics statistics : databaseSizeStatistics) {
            List<BinaryLogsStatistics> binaryLogsStatisticsList = statistics.getBinaryLogsStatistics();
            List<InformationSchemaFilesStatistics> informationSchemaFilesStatisticsList = statistics.getInformationSchemaFilesStatistics();
            try {
                binaryLogsStatisticsList.forEach(binaryLogsStatistics -> {
                    binaryLogsStatisticsMapper.insertBinaryLogsStatistics(binaryLogsStatistics);
                });

                informationSchemaFilesStatisticsList.forEach(informationSchemaFilesStatistics -> {
                    informationSchemaFilesStatisticsMapper.insertInformationSchemaFilesStatistics(informationSchemaFilesStatistics);
                });

//                databaseSizeStatisticsMapper.insertDatabaseSizeStatistics(statistics);
                LOGGER.info("Storage usage info saved for data source: {}", statistics.getBinaryLogsStatistics().get(0).getDatabaseHost());
            } catch (Exception e) {
                LOGGER.error("Error saving storage usage info for {}: {}", statistics.getBinaryLogsStatistics().get(0).getDatabaseHost(), e.getMessage(), e);
            }
        }
    }


    private String generateDatabaseUrl(DataSourceConfig config) {
        String databaseType = config.getDatabaseType();
        String host = config.getHost();
        int port = config.getPort();

        if (databaseType.equalsIgnoreCase("MySQL")) {
            return "jdbc:mysql://" + host + ":" + port + "/";
        } else if (databaseType.equalsIgnoreCase("PostgreSQL")) {
            return "jdbc:postgresql://" + host + ":" + port + "/";
        }

        return "";
    }
}
