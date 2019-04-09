package com.roc.jframeworkecharts.builder;

import com.roc.jframeworkecharts.model.YAxis;

public class YAxisBuilder {

    private LineBuilder lineBuilder;

    private String type;

    public YAxisBuilder(LineBuilder lineBuilder){
        this.lineBuilder = lineBuilder;
    }

    public YAxisBuilder type(String type){
        this.type = type;
        return this;
    }

    public YAxis build(){
        YAxis yAxis = new YAxis();
        yAxis.setType(type);
        return yAxis;
    }

    public LineBuilder endYAxis(){
        this.lineBuilder.addYAxis(build());
        return this.lineBuilder;
    }
}
