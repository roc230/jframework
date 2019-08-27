package com.roc.jframework.crawler.common;

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
     * 是否登陆
     * @param login
     * @return
     */
    ICommonCrawler login(Boolean login);

    /**
     * 设置用户名email
     * @param username
     * @return
     */
    ICommonCrawler username(String username);

    /**
     * 设置密码
     * @param password
     * @return
     */
    ICommonCrawler password(String password);

    /**
     * 是否开启无头模式
     * @param headless
     * @return
     */
    ICommonCrawler headless(Boolean headless);

    /**
     * 执行抓取
     * @param url
     */
    void execute(String url);

    void saveAsJson(Novel novel, File file);

    void saveAsTxt(Novel novel, File file);

}
