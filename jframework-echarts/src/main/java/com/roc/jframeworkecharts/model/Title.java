package com.roc.jframeworkecharts.model;

public class Title {

    /**
     * 标题
     */
    private String text = "";

    /**
     * 子标题
     */
    private String subtext = "";

    /**
     * 位置
     */
    private String x = "";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
}
