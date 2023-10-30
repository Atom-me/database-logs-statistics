package com.atom.statistics.service;

import com.atom.statistics.entity.InformationSchemaFilesStatistics;
import com.atom.statistics.mapper.InformationSchemaFilesStatisticsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Atom
 */
@Service
public class InformationSchemaFilesStatisticsService {

    @Resource
    private InformationSchemaFilesStatisticsMapper informationSchemaFilesStatisticsMapper;


    public List<InformationSchemaFilesStatistics> getFilesByDatabaseHostAndDatabasePort(String databaseHost, String databasePort) {
        return informationSchemaFilesStatisticsMapper.getFilesByDatabaseHostAndDatabasePort(databaseHost,databasePort);
    }
}
