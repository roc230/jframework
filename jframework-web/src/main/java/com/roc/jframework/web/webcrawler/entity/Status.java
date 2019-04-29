package com.roc.jframework.web.webcrawler.entity;

import java.io.Serializable;

/**
 * 小说连载状态
 */
public enum Status implements Serializable {

    /**
     * 已完结
     */
    FINISHED,

    /**
     * 连载中
     */
    ON_PROGRESS;
}
