package com.roc.jframework.web.bookmgr.entity;

import java.io.Serializable;

public class Chapter implements Serializable {

    /**
     * ID
     */
    private String id;

    /**
     * 所属书籍
     */
    private Book book;

    /**
     * 章节名称
     */
    private String name;

    /**
     * 章节内容
     */
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
