package com.roc.jframework.basic.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分布查询结果对象
 * @param <T>
 */
public class PageResult<T> implements Serializable {

    private Integer pageNum = 1;
    private Integer pageSize = 20;
    private Integer total = 0;
    private Integer pageCount = 0;
    private List<T> list;

    public PageResult(Page page, Integer total, List<T> resultList){
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = total;
        this.list = resultList;
        this.pageCount = (total % pageSize == 0) ? total / pageSize : total / pageSize + 1;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public Boolean hasNextPage(){
        return pageNum < pageCount ? true : false;
    }
}
