package com.roc.jframework.crawler.novelweb;

import java.io.Serializable;

public class NovelInfo implements Serializable {
    private String name;
    private String author;
    private String category;
    private String coverImg;
    /**
     * 状态：连载中，已完结
     */
    private String status;
    private String clickCount;
    /**
     * 字数
     */
    private String words;
    /**
     * 简介
     */
    private String brief;

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getClickCount() {
        return clickCount;
    }

    public void setClickCount(String clickCount) {
        this.clickCount = clickCount;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public static final class Builder{
        private String name;
        private String author;
        private String category;
        private String coverImg;
        private String status;
        private String clickCount;
        private String words;
        private String brief;
        private String url;
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder author(String author){
            this.author = author;
            return this;
        }
        public Builder category(String category){
            this.category = category;
            return this;
        }
        public Builder coverImg(String coverImg){
            this.coverImg = coverImg;
            return this;
        }
        public Builder status(String status){
            this.status = status;
            return this;
        }
        public Builder clickCount(String clickCount){
            this.clickCount = clickCount;
            return this;
        }
        public Builder words(String words){
            this.words = words;
            return this;
        }
        public Builder brief(String brief){
            this.brief = brief;
            return this;
        }
        public Builder url(String url){
            this.url = url;
            return this;
        }
        public NovelInfo build(){
            NovelInfo novelInfo = new NovelInfo();
            novelInfo.setName(this.name);
            novelInfo.setAuthor(this.author);
            novelInfo.setCategory(this.category);
            novelInfo.setCoverImg(this.coverImg);
            novelInfo.setStatus(this.status);
            novelInfo.setClickCount(this.clickCount);
            novelInfo.setWords(this.words);
            novelInfo.setBrief(this.brief);
            novelInfo.setUrl(this.url);
            return novelInfo;
        }
    }




}
