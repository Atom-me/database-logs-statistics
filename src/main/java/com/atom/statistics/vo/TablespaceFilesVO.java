package com.atom.statistics.vo;

/**
 * @author Atom
 */
public class TablespaceFilesVO {
    private String fileName;
    private String tablespaceName;
    private String freeExtents;
    private String totalExtents;
    private String dataFree;


    public TablespaceFilesVO(String fileName, String tablespaceName, String freeExtents, String totalExtents, String dataFree) {
        this.fileName = fileName;
        this.tablespaceName = tablespaceName;
        this.freeExtents = freeExtents;
        this.totalExtents = totalExtents;
        this.dataFree = dataFree;
    }

    public TablespaceFilesVO() {
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

    public String getFreeExtents() {
        return freeExtents;
    }

    public void setFreeExtents(String freeExtents) {
        this.freeExtents = freeExtents;
    }

    public String getTotalExtents() {
        return totalExtents;
    }

    public void setTotalExtents(String totalExtents) {
        this.totalExtents = totalExtents;
    }

    public String getDataFree() {
        return dataFree;
    }

    public void setDataFree(String dataFree) {
        this.dataFree = dataFree;
    }

    @Override
    public String toString() {
        return "TablespaceFilesVO{" +
                "fileName='" + fileName + '\'' +
                ", tablespaceName='" + tablespaceName + '\'' +
                ", freeExtents=" + freeExtents +
                ", totalExtents=" + totalExtents +
                ", dataFree=" + dataFree +
                '}';
    }
}
