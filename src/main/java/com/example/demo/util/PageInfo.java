package com.example.demo.util;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;

/**
 * The Class PageInfo.
 */
public class PageInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
     * The total records.
     */
    @ApiParam(hidden = true)
    private int totalRecords;

    /**
     * The total pages.
     */
    @ApiParam(hidden = true)
    @ApiModelProperty(hidden = true)
    private int totalPages;

    /**
     * The page size.
     */
    @ApiParam(hidden = true)
    private int pageSize = 10;

    /**
     * The current page no.
     */
    @ApiParam(hidden = true)
    private int pageNo;

    /**
     * The previous page no.
     */
    @ApiParam(hidden = true)
    @ApiModelProperty(hidden = true)
    private int previousPageNo;

    /**
     * The next page no.
     */
    @ApiParam(hidden = true)
    @ApiModelProperty(hidden = true)
    private int nextPageNo;

    /**
     * The is first page.
     */
    @ApiParam(hidden = true)
    @ApiModelProperty(hidden = true)
    private boolean isFirstPage;

    /**
     * The is last page.
     */
    @ApiParam(hidden = true)
    @ApiModelProperty(hidden = true)
    private boolean isLastPage;

    /**
     * The has previous pag.
     */
    @ApiParam(hidden = true)
    @ApiModelProperty(hidden = true)
    private boolean hasPreviousPage;

    /**
     * The has next page.
     */
    @ApiParam(hidden = true)
    @ApiModelProperty(hidden = true)
    private boolean hasNextPage;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden = true)
    private String pageAction;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden = true)
    private String pageAction3;
    /**
     * 数据授权编号
     */
    @ApiParam(hidden = true)
    @ApiModelProperty(hidden = true)
    private String dataItemCode;

    @ApiParam(hidden = true)
    @ApiModelProperty(hidden = true)
    private String accessToken;

    public PageInfo() {
        this.pageNo = 1;
    }

    public PageInfo(int totalRecords, int pageNo) {

        this(totalRecords, pageNo, 10);
    }

    /**
     * Instantiates a new page info.
     *
     * @param totalRecords the total records
     */
    public PageInfo(int totalRecords, int pageNo, int pageSize) {
        // 设置每页记录数
        setPageSize(pageSize);
        // 设置当前页
        this.pageNo = pageNo;

        infoTotalRecords(totalRecords);
    }

    public void infoTotalRecords(int totalRecords) {
        // 设置总记录数
        if (totalRecords >= 0) {
            this.totalRecords = totalRecords;
        } else {
            totalRecords = 0;
        }

        // 设置总页面数
        if (totalRecords % pageSize == 0) {
            totalPages = totalRecords / pageSize;
        } else {
            totalPages = (totalRecords / pageSize) + 1;
        }

        // 设置当前页
        if (pageNo > totalPages) {
            this.pageNo = totalPages;
        }

        // 每次设置当前页时更新上一页、下一页、是否首页、是否末页、是否有上一页、是否有下一页标志
        isFirstPage = (pageNo == 1) ? true : false;
        isLastPage = (pageNo == totalPages) ? true : false;
        hasPreviousPage = (pageNo == 1) ? false : true;
        hasNextPage = (pageNo == totalPages) ? false : true;
        previousPageNo = (hasPreviousPage) ? (pageNo - 1) : pageNo;
        nextPageNo = (hasNextPage) ? (pageNo + 1) : pageNo;
    }

    /**
     * Gets the current page no.
     *
     * @return the current page no
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * Sets the current page no.
     *
     * @param pageNo the new current page no
     */
    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            this.pageNo = 1;
        } else {
            this.pageNo = pageNo;
        }
    }

    /**
     * Gets the current page size.
     *
     * @return the current page size
     */
    @ApiModelProperty(hidden = true)
    public int getCurrentPageSize() {
        if (totalRecords == 0) {
            return 0;
        } else if (pageNo < totalPages) {
            // 非末页
            return pageSize;
        } else {
            // 末页
            return (totalRecords - (pageNo - 1) * pageSize);
        }
    }

    /**
     * Gets the current page start record.
     *
     * @return the current page start record
     */
    @ApiModelProperty(hidden = true)
    public int getCurrentPageStartRecord() {
        if (pageNo < 1) pageNo = 1;
        return (pageNo - 1) * pageSize;
    }

    /**
     * Gets the current page end record.
     *
     * @return the current page end record
     */
    @ApiModelProperty(hidden = true)
    public int getCurrentPageEndRecord() {
        return (pageNo - 1) * pageSize + getCurrentPageSize();
    }

    /**
     * Checks if is has next page.
     *
     * @return true, if is has next page
     */
    public boolean isHasNextPage() {
        return hasNextPage;
    }

    /**
     * Checks if is has previous pag.
     *
     * @return true, if is has previous pag
     */
    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    /**
     * Checks if is first page.
     *
     * @return true, if is first page
     */
    @ApiModelProperty(hidden = true)
    public boolean isFirstPage() {
        return isFirstPage;
    }

    /**
     * Checks if is last page.
     *
     * @return true, if is last page
     */
    @ApiModelProperty(hidden = true)
    public boolean isLastPage() {
        return isLastPage;
    }

    /**
     * Gets the page size.
     *
     * @return the page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the page size.
     *
     * @param pageSize the new page size
     */
    public void setPageSize(int pageSize) {
        this.pageSize = (pageSize <= 0) ? 10 : pageSize;
    }

    /**
     * Gets the previous page no.
     *
     * @return the previous page no
     */
    public int getPreviousPageNo() {
        return previousPageNo;
    }

    /**
     * Gets the next page no.
     *
     * @return the next page no
     */
    public int getNextPageNo() {
        return nextPageNo;
    }

    /**
     * Gets the total pages.
     *
     * @return the total pages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Gets the total records.
     *
     * @return the total records
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
        infoTotalRecords(this.totalRecords);
    }

    public String getPageAction() {
        return pageAction;
    }

    public void setPageAction(String pageAction) {
        this.pageAction = pageAction;
    }

    public String getDataItemCode() {
        return dataItemCode;
    }

    public void setDataItemCode(String dataItemCode) {
        this.dataItemCode = dataItemCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @ApiModelProperty(hidden = true)
    public String getPageAction2() {
        return pageAction3;
    }

    public void setPageAction2(String pageAction2) {
        this.pageAction3 = pageAction2;
    }
}