package com.atom.statistics.mapper;

import com.atom.statistics.entity.DatabaseSizeStatistics;

/**
 * @author Atom
 */
public interface DatabaseSizeStatisticsMapper {
    int insertDatabaseSizeStatistics(DatabaseSizeStatistics statistics);
}
