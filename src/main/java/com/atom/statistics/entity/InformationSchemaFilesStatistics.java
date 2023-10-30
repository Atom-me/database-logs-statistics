package com.atom.statistics.entity;

import java.time.LocalDateTime;

/**
 * @author Atom
 */
public class InformationSchemaFilesStatistics {

    private Long id;
    private String databaseHost;
    private Integer databasePort;
    private String databaseType;
    private Long fileId;
    private String fileName;
    private String tablespaceName;
    private Long freeExtents;
    private Long totalExtents;
    private Long dataFree;
    private LocalDateTime collectTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public InformationSchemaFilesStatistics(Long id, String databaseHost, Integer databasePort, String databaseType, Long fileId, String fileName, String tablespaceName, Long freeExtents, Long totalExtents, Long dataFree, LocalDateTime collectTime, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.databaseHost = databaseHost;
        this.databasePort = databasePort;
        this.databaseType = databaseType;
        this.fileId = fileId;
        this.fileName = fileName;
        this.tablespaceName = tablespaceName;
        this.freeExtents = freeExtents;
        this.totalExtents = totalExtents;
        this.dataFree = dataFree;
        this.collectTime = collectTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public InformationSchemaFilesStatistics() {
    }

    public InformationSchemaFilesStatistics(Long fileId, String fileName, String tablespaceName, Long freeExtents, Long totalExtents, Long dataFree) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.tablespaceName = tablespaceName;
        this.freeExtents = freeExtents;
        this.totalExtents = totalExtents;
        this.dataFree = dataFree;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public void setDatabaseHost(String databaseHost) {
        this.databaseHost = databaseHost;
    }

    public Integer getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(Integer databasePort) {
        this.databasePort = databasePort;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }


    public LocalDateTime getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(LocalDateTime collectTime) {
        this.collectTime = collectTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTablespaceName() {
        return tablespaceName;
    }

    public void setTablespaceName(String tablespaceName) {
        this.tablespaceName = tablespaceName;
    }

    public Long getFreeExtents() {
        return freeExtents;
    }

    public void setFreeExtents(Long freeExtents) {
        this.freeExtents = freeExtents;
    }

    public Long getTotalExtents() {
        return totalExtents;
    }

    public void setTotalExtents(Long totalExtents) {
        this.totalExtents = totalExtents;
    }

    public Long getDataFree() {
        return dataFree;
    }

    public void setDataFree(Long dataFree) {
        this.dataFree = dataFree;
    }

    @Override
    public String toString() {
        return "InformationSchemaFiles{" +
                "id=" + id +
                ", databaseHost='" + databaseHost + '\'' +
                ", databasePort=" + databasePort +
                ", databaseType='" + databaseType + '\'' +
                ", fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", tablespaceName='" + tablespaceName + '\'' +
                ", freeExtents=" + freeExtents +
                ", totalExtents=" + totalExtents +
                ", dataFree=" + dataFree +
                ", collectTime=" + collectTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
