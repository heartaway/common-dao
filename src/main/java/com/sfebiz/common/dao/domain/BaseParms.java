package com.sfebiz.common.dao.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * <p></p>
 * User: <a href="mailto:xinyuan.ymm@alibaba-inc.com">心远</a>
 * Date: 14/10/10
 * Time: 下午3:46
 */
public class BaseParms implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final Integer defaultPageSize = 20;
    private static final Integer defaultFristPage = 1;
    private static final Integer defaultTotleItem = 0;
    private Integer totalItem;
    private Integer pageSize;
    private Integer currentPage;
    private int startRow;
    private int endRow;

    protected Integer getDefaultPageSize() {
        return defaultPageSize;
    }

    public boolean isFirstPage() {
        return this.getCurrentPage() == 1;
    }

    public int getPreviousPage() {
        int back = this.getCurrentPage() - 1;
        if (back <= 0) {
            back = 1;
        }
        return back;
    }

    public boolean isLastPage() {
        return this.getTotalPage() <= this.getCurrentPage();
    }

    public Integer getNextPage() {
        int back = this.getCurrentPage() + 1;
        if (back > this.getTotalPage()) {
            return null;
        }
        return back;
    }

    public Integer getCurrentPage() {
        if (currentPage == null) {
            return defaultFristPage;
        }
        return currentPage;
    }

    public void setCurrentPageString(String pageString) {
        if (isBlankString(pageString)) {
            this.setCurrentPage(defaultFristPage);
        }

        try {
            Integer integer = new Integer(pageString);

            this.setCurrentPage(integer);
        } catch (NumberFormatException ignore) {
            this.setCurrentPage(defaultFristPage);
        }
    }

    public void setCurrentPage(Integer cPage) {
        if ((cPage == null) || (cPage <= 0)) {
            this.currentPage = null;
        } else {
            this.currentPage = cPage;
        }
        setStartEndRow();
    }

    private void setStartEndRow() {
        this.startRow = this.getPageSize() * (this.getCurrentPage() - 1) + 1;
        this.endRow = this.startRow + this.getPageSize() - 1;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return getDefaultPageSize();
        }

        return pageSize;
    }

    public boolean hasSetPageSize() {
        return pageSize != null;
    }

    public void setPageSizeString(String pageSizeString) {
        if (isBlankString(pageSizeString)) {
            return;
        }

        try {
            Integer integer = new Integer(pageSizeString);

            this.setPageSize(integer);
        } catch (NumberFormatException ignore) {
        }
    }

    private boolean isBlankString(String pageSizeString) {
        return pageSizeString == null || pageSizeString.trim().length() == 0;

    }

    public void setPageSize(Integer pSize) {
        if ((pSize == null) || (pSize <= 0)) {
            this.pageSize = null;
        } else {
            this.pageSize = pSize;
        }
        setStartEndRow();
    }

    public Integer getTotalItem() {
        if (totalItem == null) {
            return defaultTotleItem;
        }

        return totalItem;
    }

    public void setTotalItem(Integer tItem) {
        if (tItem == null) {
            throw new IllegalArgumentException("TotalItem can't be null.");
        }
        this.totalItem = tItem;
        int current = this.getCurrentPage();
        int lastPage = this.getTotalPage();

        if (current > lastPage) {
            this.setCurrentPage(lastPage);
        }
    }

    public int getTotalPage() {
        int pgSize = this.getPageSize();
        int total = this.getTotalItem();
        int result = total / pgSize;

        if ((total == 0) || ((total % pgSize) != 0)) {
            result++;
        }

        return result;
    }

    public int getPageFirstItem() {
        int cPage = this.getCurrentPage();
        if (cPage == 1) {
            return 1; // 第一页开始当然是第 1 条记录
        }
        cPage--;
        int pgSize = this.getPageSize();
        return (pgSize * cPage) + 1;
    }

    public int getPageLastItem() {
        int cPage = this.getCurrentPage();
        int pgSize = this.getPageSize();
        int assumeLast = pgSize * cPage;
        int totalItem = getTotalItem();

        if (assumeLast > totalItem) {
            return totalItem;
        } else {
            return assumeLast;
        }
    }

    /**
     * @return Returns the endRow.
     */
    public int getEndRow() {
        return endRow;
    }

    /**
     * @param endRow The endRow to set.
     */
    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    /**
     * @return Returns the startRow.
     */
    public int getStartRow() {
        return startRow;
    }

    /**
     * @return Returns the startRow.
     */
    public int getStartRowForMysql() {
        return startRow > 0 ? (startRow - 1) : 0;
    }

    /**
     * @param startRow The startRow to set.
     */
    private void setStartRow(int startRow) {
        this.startRow = startRow;
    }


    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }


    public static void main(String[] args) {
        BaseParms bp = new BaseParms();
        bp.setCurrentPage(1);
        bp.setPageSize(20);
        System.out.println(bp.getStartRowForMysql());
        System.out.println(bp.getStartRow());
        System.out.println(bp.getEndRow());
    }
}
