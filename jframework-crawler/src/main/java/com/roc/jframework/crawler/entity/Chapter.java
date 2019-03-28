package com.roc.jframework.crawler.entity;

import java.util.List;

public class Chapter {
    /**
     * 标题
     */
    private String title;
    /**
     * 段落
     */
    private List<String> paragraphs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<String> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
