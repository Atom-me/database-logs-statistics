package com.atom.statistics.mapper;

import com.atom.statistics.entity.BinaryLogsStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Atom
 */
public interface BinaryLogsStatisticsMapper {
    int insertBinaryLogsStatistics(BinaryLogsStatistics statistics);

    List<BinaryLogsStatistics> getBinLogFilesByDatabaseHostAndDatabasePort(@Param("databaseHost") String databaseHost, @Param("databasePort") String databasePort);
}
