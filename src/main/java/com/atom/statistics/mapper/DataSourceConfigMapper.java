package com.atom.statistics.mapper;

import com.atom.statistics.entity.DataSourceConfig;

import java.util.List;

/**
 * @author Atom
 */
public interface DataSourceConfigMapper {
    int insertDataSourceConfig(DataSourceConfig config);

    List<DataSourceConfig> getAllDataSourceConfigs();


}
