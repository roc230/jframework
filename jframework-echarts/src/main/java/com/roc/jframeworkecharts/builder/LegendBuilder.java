package com.roc.jframeworkecharts.builder;

import com.roc.jframeworkecharts.model.Legend;

import java.util.ArrayList;
import java.util.List;

public class LegendBuilder {

    private LineBuilder lineBuilder;

    private List<String> data;
    private String orient = "";
    private String left = "";

    public LegendBuilder(LineBuilder lineBuilder){
        this.lineBuilder = lineBuilder;
        this.data = new ArrayList<>();
    }

    public Legend build(){
        Legend legend = new Legend();
        legend.setData(data);
        return legend;
    }

    public LegendBuilder addData(String str){
        this.data.add(str);
        return this;
    }

    public LegendBuilder orient(String orient){
        this.orient = orient;
        return this;
    }

    public LegendBuilder left(String left){
        this.left = left;
        return this;
    }

    public LineBuilder endLengend(){
        this.lineBuilder.setLegend(build());
        return this.lineBuilder;
    }
}
