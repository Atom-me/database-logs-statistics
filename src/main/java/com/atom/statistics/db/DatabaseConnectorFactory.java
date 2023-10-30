package com.atom.statistics.db;

/**
 * @author Atom
 */
public class DatabaseConnectorFactory {
    public static DatabaseConnector createConnector(String databaseType) {
        if (databaseType.equalsIgnoreCase("MySQL")) {
            return new MySQLDatabaseConnector();
        } else if (databaseType.equalsIgnoreCase("PostgreSQL")) {
            return new PostgreSQLDatabaseConnector();
        }
        return null;
    }
}