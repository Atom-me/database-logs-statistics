package com.atom.statistics.mapper;

import com.atom.statistics.entity.InformationSchemaFilesStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Atom
 */
public interface InformationSchemaFilesStatisticsMapper {
    int insertInformationSchemaFilesStatistics(InformationSchemaFilesStatistics statistics);

    List<InformationSchemaFilesStatistics> getFilesByDatabaseHostAndDatabasePort(@Param("databaseHost") String databaseHost, @Param("databasePort") String databasePort);
}
