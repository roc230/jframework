package com.roc.jframeworkecharts.builder;

import com.roc.jframeworkecharts.model.Title;

public class TitleBuilder {

    private String text = "";
    private String subtext = "";
    private String x = "";

    private LineBuilder lineBuilder;

    public TitleBuilder(LineBuilder lineBuilder){
        this.lineBuilder = lineBuilder;
    }

    public TitleBuilder text(String title){
        this.text = title;
        return this;
    }

    public TitleBuilder subtext(String subtext){
        this.subtext = subtext;
        return this;
    }

    public TitleBuilder x(String x){
        this.x = x;
        return this;
    }

    public Title build(){
        Title title = new Title();
        title.setText(text);
        title.setSubtext(subtext);
        title.setX(x);
        return title;
    }

    public LineBuilder endTitle(){
        this.lineBuilder.setTitle(build());
        return this.lineBuilder;
    }
}
