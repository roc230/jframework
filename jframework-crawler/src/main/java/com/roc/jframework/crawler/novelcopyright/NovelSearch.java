package com.roc.jframework.crawler.novelcopyright;

import java.io.Serializable;

public class NovelSearch implements Serializable {

    private String name;
    private String coverImg;
    private String url;
    private String words;
    private String author;
    private String category;
    private String score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public static final class Builder{
        private String name;
        private String coverImg;
        private String url;
        private String words;
        private String author;
        private String category;
        private String score;
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder coverImg(String coverImg){
            this.coverImg = coverImg;
            return this;
        }
        public Builder url(String url){
            this.url = url;
            return this;
        }
        public Builder words(String words){
            this.words = words;
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
        public Builder score(String score){
            this.score = score;
            return this;
        }
        public NovelSearch build(){
            NovelSearch novelSearch = new NovelSearch();
            novelSearch.setName(this.name);
            novelSearch.setCoverImg(this.coverImg);
            novelSearch.setUrl(this.url);
            novelSearch.setWords(this.words);
            novelSearch.setAuthor(this.author);
            novelSearch.setCategory(this.category);
            novelSearch.setScore(this.score);
            return novelSearch;
        }
    }
}
