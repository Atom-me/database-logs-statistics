package com.atom.statistics.db;

import com.atom.statistics.entity.BinaryLogsStatistics;
import com.atom.statistics.entity.DatabaseSizeStatistics;
import com.atom.statistics.entity.InformationSchemaFilesStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Atom
 */
public class MySQLDatabaseConnector implements DatabaseConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySQLDatabaseConnector.class);

    private Connection connection;

    @Override
    public void connect(String databaseUrl, String username, String password) {
        try {
            LOGGER.info("Connecting to MySQL database...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseUrl, username, password);
            LOGGER.info("Connected to MySQL database successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Failed to connect to MySQL database!", e);
            throw new RuntimeException("Failed to connect to MySQL database!", e);
        }
    }
    @Override
    public List<DatabaseSizeStatistics> executeQueryAndGetStorageUsage() {
        try {
            List<BinaryLogsStatistics> binaryLogsStatisticsList = executeBinaryLogsQuery();
            List<InformationSchemaFilesStatistics> informationSchemaFilesStatisticsList = executeInformationSchemaQuery();

            DatabaseSizeStatistics databaseSizeStatistics = new DatabaseSizeStatistics(binaryLogsStatisticsList, informationSchemaFilesStatisticsList);
            return Collections.singletonList(databaseSizeStatistics);
        } catch (SQLException e) {
            LOGGER.error("Failed to execute query or retrieve storage usage!", e);
            throw new RuntimeException("Failed to execute query or retrieve storage usage!", e);
        }
    }

    private List<BinaryLogsStatistics> executeBinaryLogsQuery() throws SQLException {
        String binlogSql = "show binary logs";
        LOGGER.info("Executing MySQL query: {}", binlogSql);

        try (PreparedStatement binlogStatement = connection.prepareStatement(binlogSql);
             ResultSet binlogResultSet = binlogStatement.executeQuery()) {

            List<BinaryLogsStatistics> binaryLogsStatisticsList = new ArrayList<>();
            while (binlogResultSet.next()) {
                String logName = binlogResultSet.getString("Log_name");
                BigDecimal fileSize = binlogResultSet.getBigDecimal("File_size");
                String encrypted = binlogResultSet.getString("Encrypted");

                BinaryLogsStatistics binaryLogsStatistics = new BinaryLogsStatistics(logName, fileSize, encrypted);
                binaryLogsStatisticsList.add(binaryLogsStatistics);
            }

            LOGGER.info("Binary logs query execution completed.");
            return binaryLogsStatisticsList;
        }
    }

    private List<InformationSchemaFilesStatistics> executeInformationSchemaQuery() throws SQLException {
        String informationSchemaSql = "SELECT FILE_ID,FILE_NAME,TABLESPACE_NAME,FREE_EXTENTS,TOTAL_EXTENTS,DATA_FREE from information_schema.FILES";
        LOGGER.info("Executing MySQL query: {}", informationSchemaSql);

        try (PreparedStatement informationSchemaStatement = connection.prepareStatement(informationSchemaSql);
             ResultSet informationSchemaResultSet = informationSchemaStatement.executeQuery()) {

            List<InformationSchemaFilesStatistics> informationSchemaFilesStatisticsList = new ArrayList<>();
            while (informationSchemaResultSet.next()) {
                Long fileId = informationSchemaResultSet.getLong("FILE_ID");
                String fileName = informationSchemaResultSet.getString("FILE_NAME");
                String tablespaceName = informationSchemaResultSet.getString("TABLESPACE_NAME");
                Long freeExtents = informationSchemaResultSet.getLong("FREE_EXTENTS");
                Long totalExtents = informationSchemaResultSet.getLong("TOTAL_EXTENTS");
                Long dataFree = informationSchemaResultSet.getLong("DATA_FREE");

                InformationSchemaFilesStatistics informationSchemaFilesStatistics = new InformationSchemaFilesStatistics(fileId, fileName, tablespaceName, freeExtents, totalExtents, dataFree);
                informationSchemaFilesStatisticsList.add(informationSchemaFilesStatistics);
            }

            LOGGER.info("Information schema query execution completed.");
            return informationSchemaFilesStatisticsList;
        }
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                LOGGER.info("Disconnected from MySQL database.");
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to disconnect from MySQL database!", e);
            throw new RuntimeException("Failed to disconnect from MySQL database!", e);
        }
    }
}