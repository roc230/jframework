package com.roc.jframework.web.bookmgr.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 书箱日统计数据
 */
@Entity
@Table(name = "bm_book_day_statistics")
public class BookDayStatistics implements Serializable {

    /**
     * ID
     */
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    private String id;

    /**
     * 日期
     */
    @Column(name = "date")
    private Date date;

    /**
     * 所属书
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    /**
     * 访问量
     */
    @Column(name = "page_view")
    private Long pageView;

    /**
     * 唯一用户访问量
     */
    @Column(name = "unique_view")
    private Long uniqueView;

    /**
     * 充值人数
     */
    @Column(name = "recharge_count")
    private Long rechargeCount;

    /**
     * 充值金额
     */
    @Column(name = "recharge_amount")
    private Double rechargeAmount;

    /**
     * 消费人数
     */
    @Column(name = "consumer_count")
    private Long consumerCount;

    /**
     * 消费金额
     */
    @Column(name = "consume_amount")
    private Long consumeAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getPageView() {
        return pageView;
    }

    public void setPageView(Long pageView) {
        this.pageView = pageView;
    }

    public Long getUniqueView() {
        return uniqueView;
    }

    public void setUniqueView(Long uniqueView) {
        this.uniqueView = uniqueView;
    }

    public Long getRechargeCount() {
        return rechargeCount;
    }

    public void setRechargeCount(Long rechargeCount) {
        this.rechargeCount = rechargeCount;
    }

    public Double getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(Double rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Long getConsumerCount() {
        return consumerCount;
    }

    public void setConsumerCount(Long consumerCount) {
        this.consumerCount = consumerCount;
    }

    public Long getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Long consumeAmount) {
        this.consumeAmount = consumeAmount;
    }
}
