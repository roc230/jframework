package com.roc.jframework.web.rightmgr.vo;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public abstract class PageVO<T> {

    private long totalElements = 0;
    private int totalPages = 0;
    private boolean hasNext;
    private boolean hasPrevios;
    private boolean hasContent;
    private List<T> content;
    private int pageNum;
    private int pageSize;

    public PageVO(Page page){
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.hasNext = page.hasNext();
        this.hasPrevios = page.hasPrevious();
        this.hasContent = page.hasContent();
        this.content = this.transform(page.getContent());
        this.pageNum = page.getNumber();
        this.pageSize = page.getSize();

    }

    private <K> List<T> transform(List<K> list){
        List<T> volist = new ArrayList<>(list.size());
        for(K obj : list){
            volist.add(transform(obj));
        }
        return volist;
    }

    public abstract <K> T transform(K obj);

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isHasPrevios() {
        return hasPrevios;
    }

    public boolean isHasContent() {
        return hasContent;
    }

    public List<T> getContent() {
        return content;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }
}
