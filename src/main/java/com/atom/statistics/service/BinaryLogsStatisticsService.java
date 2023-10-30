package com.atom.statistics.service;

import com.atom.statistics.common.PageResult;
import com.atom.statistics.entity.BinaryLogsStatistics;
import com.atom.statistics.mapper.BinaryLogsStatisticsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Atom
 */
@Service
public class BinaryLogsStatisticsService {

    @Resource
    private BinaryLogsStatisticsMapper binaryLogsStatisticsMapper;


    public List<BinaryLogsStatistics> getBinLogFilesByDatabaseHostAndDatabasePort(String databaseHost, String databasePort) {
        return binaryLogsStatisticsMapper.getBinLogFilesByDatabaseHostAndDatabasePort(databaseHost, databasePort);
    }
}
