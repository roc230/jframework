package com.roc.jframeworkecharts.model.line;

public class YAxisBuilder {
    private LineOptionBuilder lineOptionBuilder;
    private YAxis yAxis;

    public YAxisBuilder(LineOptionBuilder lineOptionBuilder){
        this.lineOptionBuilder = lineOptionBuilder;
        this.yAxis = new YAxis();
    }

    public YAxisBuilder type(String type){
        this.yAxis.setType(type);
        return this;
    }

    public YAxis build(){
        return this.yAxis;
    }

    public LineOptionBuilder endYAxis(){
        this.lineOptionBuilder.setyAxis(this.build());
        return this.lineOptionBuilder;
    }
}
