package com.atom.statistics.entity;

import java.util.List;

/**
 * @author Atom
 */
public class DatabaseSizeStatistics {

    private List<BinaryLogsStatistics> binaryLogStatistics;
    private List<InformationSchemaFilesStatistics> informationSchemaFileStatistics;

    public DatabaseSizeStatistics(List<BinaryLogsStatistics> binaryLogStatistics, List<InformationSchemaFilesStatistics> informationSchemaFileStatistics) {
        this.binaryLogStatistics = binaryLogStatistics;
        this.informationSchemaFileStatistics = informationSchemaFileStatistics;
    }

    public DatabaseSizeStatistics() {
    }

    public List<BinaryLogsStatistics> getBinaryLogsStatistics() {
        return binaryLogStatistics;
    }

    public void setBinaryLogs(List<BinaryLogsStatistics> binaryLogStatistics) {
        this.binaryLogStatistics = binaryLogStatistics;
    }

    public List<InformationSchemaFilesStatistics> getInformationSchemaFilesStatistics() {
        return informationSchemaFileStatistics;
    }

    public void setInformationSchemaFiles(List<InformationSchemaFilesStatistics> informationSchemaFileStatistics) {
        this.informationSchemaFileStatistics = informationSchemaFileStatistics;
    }

    @Override
    public String toString() {
        return "DatabaseSizeStatistics{" +
                "binaryLogStatistics=" + binaryLogStatistics +
                ", informationSchemaFileStatistics=" + informationSchemaFileStatistics +
                '}';
    }
}
