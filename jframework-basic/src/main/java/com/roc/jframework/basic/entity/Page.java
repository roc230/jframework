package com.roc.jframework.basic.entity;

import java.io.Serializable;

/**
 * 分布参数对象
 */
public class Page implements Serializable {
    private Integer start = 0;
    private Integer pageSize = 20;
    private Integer pageNum = 1;

    public Page(Integer pageNum, Integer pageSize){
        this.pageSize = pageSize;
        this.start = (pageNum - 1) * pageSize;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }
}
