package com.atom.statistics.service;

import com.atom.statistics.entity.DatabaseSizeStatistics;
import com.atom.statistics.mapper.DatabaseSizeStatisticsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Atom
 */
@Service
public class DatabaseSizeStatisticsService {

    @Resource
    private DatabaseSizeStatisticsMapper statisticsMapper;


    public void insertDatabaseSizeStatistics(DatabaseSizeStatistics statistics) {
        statisticsMapper.insertDatabaseSizeStatistics(statistics);
    }

}
