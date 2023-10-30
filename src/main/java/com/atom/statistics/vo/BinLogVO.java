package com.atom.statistics.vo;

/**
 * @author Atom
 */
public class BinLogVO {
    private String logName;
    private String fileSize;


    public BinLogVO() {
    }

    public BinLogVO(String logName, String fileSize) {
        this.logName = logName;
        this.fileSize = fileSize;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "BinLogVO{" +
                "logName='" + logName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                '}';
    }
}
