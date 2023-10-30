package com.atom.statistics.common;

import com.atom.statistics.entity.BinaryLogsStatistics;

import java.util.List;
import java.util.Objects;

/**
 * @author Atom
 */
public class PageResult<T> {
    private Long pageNum;
    private Long pageSize;
    private List<T> dataList;
    private Long total;


    public PageResult(Long pageNum, Long pageSize, List<T> dataList, Long total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.dataList = dataList;
        this.total = total;
    }

    public PageResult() {
    }

    public static PageResult<BinaryLogsStatistics> build(List<BinaryLogsStatistics> binaryLogsStatisticsList) {
        PageResult pageResult = new PageResult();

        return null;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", dataList=" + dataList +
                ", total=" + total +
                '}';
    }


//    public static PageResult builder(IPage<?> iPage, List<?> data) {
//        PageResult pageResult = new PageResult();
//        pageResult.setPageNum(iPage.getCurrent());
//        pageResult.setPageSize(iPage.getSize());
//        pageResult.setTotal(iPage.getTotal());
//        pageResult.setDataList(iPage.getRecords());
//        if (Objects.nonNull(data)) {
//            pageResult.setDataList(data);
//        }
//        return pageResult;
//    }
}
