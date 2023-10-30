package com.atom.statistics.db;

import com.atom.statistics.entity.DatabaseSizeStatistics;

import java.util.List;

/**
 * @author Atom
 */
public interface DatabaseConnector {
    void connect(String databaseUrl, String username, String password);

    /**
     * 执行查询SQL语句获取数据库占用存储详情
     *
     * @return
     */
    List<DatabaseSizeStatistics> executeQueryAndGetStorageUsage();

    void disconnect();
}
