package com.roc.jframeworkecharts.model.line;

public class TitleBuilder {
    private LineOptionBuilder lineOptionBuilder;
    private Title title;

    public TitleBuilder(LineOptionBuilder lineOptionBuilder){
        this.lineOptionBuilder = lineOptionBuilder;
        this.title = new Title();
    }

    public TitleBuilder text(String text){
        this.title.setText(text);
        return this;
    }

    public Title build(){
        return this.title;
    }

    public LineOptionBuilder endTitle(){
        this.lineOptionBuilder.setTitle(this.build());
        return this.lineOptionBuilder;
    }
}
