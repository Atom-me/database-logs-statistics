package com.atom.statistics.db;

import com.atom.statistics.entity.DatabaseSizeStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Atom
 */
public class PostgreSQLDatabaseConnector implements DatabaseConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgreSQLDatabaseConnector.class);

    private Connection connection;

    @Override
    public void connect(String databaseUrl, String username, String password) {
        try {
            LOGGER.info("Connecting to PostgreSQL database...");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseUrl, username, password);
            LOGGER.info("Connected to PostgreSQL database successfully.");
        } catch (Exception e) {
            LOGGER.error("Failed to connect to PostgreSQL database!", e);
            throw new RuntimeException("Failed to connect to PostgreSQL database!", e);
        }
    }

    @Override
    public List<DatabaseSizeStatistics> executeQueryAndGetStorageUsage() {
        List<DatabaseSizeStatistics> statisticsList = new ArrayList<>();

        try {
            String sql = "SELECT d.datname AS \"DATABASE_NAME\",  \n" +
                    "\t\tCASE WHEN pg_catalog.has_database_privilege(d.datname, 'CONNECT')\n" +
                    "        THEN pg_catalog.pg_database_size(d.datname)::bigint\n" +
                    "        ELSE 0\n" +
                    "    END AS \"DATABASE_SIZE\"\n" +
                    "FROM pg_catalog.pg_database d\n" +
                    "ORDER BY\n" +
                    "    CASE WHEN pg_catalog.has_database_privilege(d.datname, 'CONNECT')\n" +
                    "        THEN pg_catalog.pg_database_size(d.datname)::bigint\n" +
                    "        ELSE NULL\n" +
                    "    END DESC\n" +
                    "LIMIT 20;\n";

            LOGGER.info("Executing PostgreSQL query: {}", sql);

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String databaseName = resultSet.getString("DATABASE_NAME");
                BigDecimal databaseSize = resultSet.getBigDecimal("DATABASE_SIZE");

                DatabaseSizeStatistics sizeStatistics = new DatabaseSizeStatistics();
//                sizeStatistics.setDatabaseName(databaseName);
//                sizeStatistics.setDatabaseSize(databaseSize);

                statisticsList.add(sizeStatistics);
            }

            resultSet.close();
            statement.close();
            LOGGER.info("Query execution completed.");
        } catch (SQLException e) {
            LOGGER.error("Failed to execute query or retrieve storage usage!", e);
            throw new RuntimeException("Failed to execute query or retrieve storage usage!", e);
        }

        return statisticsList;
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                LOGGER.info("Disconnected from PostgreSQL database.");
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to disconnect from PostgreSQL database!", e);
            throw new RuntimeException("Failed to disconnect from PostgreSQL database!", e);
        }
    }
}