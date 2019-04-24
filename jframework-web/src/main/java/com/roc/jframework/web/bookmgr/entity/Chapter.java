package com.roc.jframework.web.bookmgr.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bm_chapter")
public class Chapter implements Serializable {

    /**
     * ID
     */
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 36)
    private String id;

    /**
     * 所属书籍
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    /**
     * 章节名称
     */
    @Column(name = "name", length = 36)
    private String name;

    /**
     * 章节内容
     */
    @Column(name = "content", columnDefinition = "text")
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
