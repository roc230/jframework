package com.roc.jframeworkecharts.model.line;

import java.util.List;

public class XAxisBuilder {
    private LineOptionBuilder lineOptionBuilder;
    private XAxis xAxis;

    public XAxisBuilder(LineOptionBuilder lineOptionBuilder){
        this.lineOptionBuilder = lineOptionBuilder;
        this.xAxis = new XAxis();
    }

    public XAxisBuilder type(String type){
        this.xAxis.setType(type);
        return this;
    }

    public XAxisBuilder boundaryGap(Boolean boundaryGap){
        this.xAxis.setBoundaryGap(boundaryGap);
        return this;
    }

    public XAxisBuilder addData(String data){
        this.xAxis.addData(data);
        return this;
    }

    public XAxisBuilder setData(List<String> data){
        this.xAxis.setData(data);
        return this;
    }

    public XAxis build(){
        return this.xAxis;
    }

    public LineOptionBuilder endXAxis(){
        this.lineOptionBuilder.setxAxis(this.build());
        return this.lineOptionBuilder;
    }
}
