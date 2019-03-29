package com.roc.jframework.crawler;

import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Novel;

import java.io.File;

public abstract class BasicCrawler implements ICommonCrawler{

    protected Integer start = 0;
    protected Integer max = 1;
    protected Boolean append = false;

    @Override
    public BasicCrawler start(Integer start){
        this.start = start;
        return this;
    }

    @Override
    public BasicCrawler max(Integer max){
        this.max = max;
        return this;
    }

    @Override
    public BasicCrawler append(Boolean append){
        this.append = append;
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
