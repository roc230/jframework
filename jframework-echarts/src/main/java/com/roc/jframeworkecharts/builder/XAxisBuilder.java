package com.roc.jframeworkecharts.builder;

import com.roc.jframeworkecharts.model.XAxis;

import java.util.List;

public class XAxisBuilder {

    private LineBuilder lineBuilder;

    private String type;
    private List<String> data;
    private Boolean boundaryGap = false;

    public XAxisBuilder(LineBuilder lineBuilder){
        this.lineBuilder = lineBuilder;
    }

    public XAxis build(){
        XAxis xAxis = new XAxis();
        xAxis.setType(type);
        xAxis.setData(data);
        return xAxis;
    }

    public XAxisBuilder type(String type){
        this.type = type;
        return this;
    }

    public XAxisBuilder data(List<String> data){
        this.data = data;
        return this;
    }

    public XAxisBuilder boundaryGap(Boolean boundaryGap){
        this.boundaryGap = boundaryGap;
        return this;
    }

    public LineBuilder endAXis(){
        lineBuilder.addXAxis(build());
        return lineBuilder;
    }
}
