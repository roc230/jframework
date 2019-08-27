package com.roc.jframework.crawler.entity;

import java.util.List;

public class Novel {
    /**
     * 名称
     */
    private String name;
    /**
     * 封面
     */
    private String cover;
    /**
     * 作者
     */
    private String author;
    /**
     * 类别
     */
    private String category;
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

    public Novel(){}

    public Novel(Builder builder){
        this.setUrl(builder.url);
        this.setAuthor(builder.author);
        this.setChapters(builder.chapters);
        this.setDirectories(builder.directories);
        this.setCover(builder.cover);
        this.setName(builder.name);
        this.setCategory(builder.category);
    }

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static class Builder{
        private String name;
        private String cover;
        private String author;
        private String category;
        private String url;
        private List<Directory> directories;
        private List<Chapter> chapters;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder cover(String cover){
            this.cover = cover;
            return this;
        }

        public Builder category(String category){
            this.category = category;
            return this;
        }

        public Builder author(String author){
            this.author = author;
            return this;
        }

        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder directories(List<Directory> directories){
            this.directories = directories;
            return this;
        }

        public Builder chapters(List<Chapter> chapters){
            this.chapters = chapters;
            return this;
        }

        public Novel build(){
            Novel novel = new Novel(this);
            return novel;
        }
    }
}
