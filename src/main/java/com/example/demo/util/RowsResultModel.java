package com.example.demo.util;


import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */

public class RowsResultModel<T> extends BaseResultModel<T> {

    private List<T> list;
    private long total;
    private PageInfo pageInfo;

    public RowsResultModel(List<T> list, long total) {
        this.list = list;
        this.total = total;
    }

    public RowsResultModel(List<T> list, PageInfo pageInfo) {
        this.list = list;
        this.pageInfo = pageInfo;
        this.total = this.pageInfo.getTotalRecords();
    }

    /**
     * 不能直接设置list,必须一起设置total,请使用带total 或 pageInfo的构造函数
     */
    @Deprecated()
    public RowsResultModel(List<T> list) {
        this.list = list;
        if (list != null) {
            this.total = list.size();
        }
    }

    public List<T> getRows() {
        return list;
    }

    public long getTotal() {
        return total;
    }

//    public List<T> getList() {
//        return list;
//    }

    public RowsResultModel<T> setList(List<T> list, long total) {
        this.list = list;
        this.total = total;
        return this;
    }

    public RowsResultModel<T> setList(List<T> list, PageInfo pageInfo) {
        setList(list, pageInfo.getTotalRecords());
        this.pageInfo = pageInfo;
        return this;
    }

    /**
     * 不能直接设置list,必须一起设置total,请使用带total 或 pageInfo的set方法
     */
    @Deprecated()
    public RowsResultModel<T> setList(List<T> list) {
        this.list = list;
        if (list != null) {
            this.total = list.size();
        }
        return this;
    }

    public PageInfo getPageInfo() {
        if (this.pageInfo == null) {
            this.pageInfo = new PageInfo();
        }
        return pageInfo;
    }

    public RowsResultModel<T> setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        if (this.total == 0) {
            this.total = pageInfo.getTotalRecords();
        }
        return this;
    }

    public RowsResultModel() {
        super();
        if (this.pageInfo == null) {
            this.pageInfo = new PageInfo();
        }
    }


}
