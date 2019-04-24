package com.roc.jframework.web.bookmgr.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 书箱日统计数据
 */
public class BookDayStatistics implements Serializable {

    /**
     * ID
     */
    private String id;

    /**
     * 日期
     */
    private Date day;

    /**
     * 所属书
     */
    private Book book;

    /**
     * 访问量
     */
    private Long pageView;

    /**
     * 唯一用户访问量
     */
    private Long uniqueView;

    /**
     * 充值人数
     */
    private Long rechargeCount;

    /**
     * 充值金额
     */
    private Double rechargeAmount;

    /**
     * 消费人数
     */
    private Long consumerCount;

    /**
     * 消费金额
     */
    private Long consumeAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
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
