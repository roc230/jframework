package com.roc.jframework.crawler;

import com.roc.jframework.crawler.entity.Novel;

import java.io.File;

public interface ICommonCrawler {

    /**
     * 开始下标
     * @param start
     */
    ICommonCrawler start(Integer start);

    /**
     * 章节数目
     * @param max
     */
    ICommonCrawler max(Integer max);

    /**
     * 保存方式是否为追加内容
     * @param append
     */
    ICommonCrawler append(Boolean append);

    /**
     * 执行抓取
     * @param url
     */
    void execute(String url);

    void saveAsJson(Novel novel, File file);

    void saveAsTxt(Novel novel, File file);
}
