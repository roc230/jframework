package com.roc.jframework.crawler;

import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Novel;

import java.io.File;

public abstract class AbstractCrawler implements ICommonCrawler{

    protected Integer start = 0;
    protected Integer max = 1;
    protected Boolean append = false;
    protected Boolean login = false;
    protected String username;
    protected String password;

    @Override
    public AbstractCrawler start(Integer start){
        this.start = start;
        return this;
    }

    @Override
    public AbstractCrawler max(Integer max){
        this.max = max;
        return this;
    }

    @Override
    public AbstractCrawler append(Boolean append){
        this.append = append;
        return this;
    }

    @Override
    public ICommonCrawler login(Boolean login) {
        this.login = login;
        return this;
    }

    @Override
    public ICommonCrawler username(String username) {
        this.username = username;
        return this;
    }

    @Override
    public ICommonCrawler password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public abstract void execute(String url);

    @Override
    public void saveAsJson(Novel novel, File file) {
        FileUtils.saveAsFile(JsonUtils.toString(novel), file, true);
    }

    @Override
    public void saveAsTxt(Novel novel, File file) {
        StringBuffer sb = new StringBuffer();
        for(Chapter chapter : novel.getChapters()){
            String name = chapter.getTitle();
            sb.append("\r\n")
                    .append(name)
                    .append("\r\n")
                    .append("\r\n");
            for(String p : chapter.getParagraphs()){
                sb.append("\0\0\0\0\0\0\0\0")
                        .append(p)
                        .append("\r\n")
                        .append("\r\n");
            }
        }
        FileUtils.saveAsFile(sb.toString(), file, true);
    }
}
