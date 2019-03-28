package com.roc.jframework.crawler.entity;

import java.util.List;

public class Novel {
    /**
     * 名称
     */
    private String name;
    /**
     * 入口url
     */
    private String url;
    /**
     * 目录
     */
    private List<Directory> directories;
    /**
     * 章节
     */
    private List<Chapter> chapters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(List<Directory> directories) {
        this.directories = directories;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
}
