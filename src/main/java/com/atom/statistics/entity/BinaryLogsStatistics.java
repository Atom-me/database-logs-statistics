package com.atom.statistics.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Atom
 */
public class BinaryLogsStatistics {

    private Long id;
    private String databaseHost;
    private Integer databasePort;
    private String logName;
    private BigDecimal fileSize;
    private String encrypted;
    private LocalDateTime collectTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public BinaryLogsStatistics(Long id, String databaseHost, Integer databasePort, String logName, BigDecimal fileSize, String encrypted, LocalDateTime collectTime, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.databaseHost = databaseHost;
        this.databasePort = databasePort;
        this.logName = logName;
        this.fileSize = fileSize;
        this.encrypted = encrypted;
        this.collectTime = collectTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public BinaryLogsStatistics() {
    }

    public BinaryLogsStatistics(String logName, BigDecimal fileSize, String encrypted) {
        this.logName = logName;
        this.fileSize = fileSize;
        this.encrypted = encrypted;
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

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public BigDecimal getFileSize() {
        return fileSize;
    }

    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
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


    @Override
    public String toString() {
        return "BinaryLogs{" +
                "id=" + id +
                ", databaseHost='" + databaseHost + '\'' +
                ", databasePort=" + databasePort +
                ", logName='" + logName + '\'' +
                ", fileSize=" + fileSize +
                ", encrypted='" + encrypted + '\'' +
                ", collectTime=" + collectTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
