package com.roc.jframeworkecharts.builder;

import com.roc.jframeworkecharts.model.ItemStyle;
import com.roc.jframeworkecharts.model.Series;

import java.util.ArrayList;
import java.util.List;

public class SeriesBuilder<T> {

    private LineBuilder lineBuilder;

    private String name = "";
    private String type = "";
    private List<T> data;
    private String radius = "";
    private List<String> center;
//    private ItemStyle itemStyle;


    public SeriesBuilder(LineBuilder lineBuilder) {
        this.lineBuilder = lineBuilder;
    }

    public SeriesBuilder name(String name){
        this.name = name;
        return this;
    }

    public SeriesBuilder type(String type){
        this.type = type;
        return this;
    }

    public SeriesBuilder data(List<T> data){
        this.data = data;
        return this;
    }

    public SeriesBuilder redius(String radius){
        this.radius = radius;
        return this;
    }

    public SeriesBuilder center(String center){
        if(this.center == null){
            this.center = new ArrayList<>();
        }
        this.center.add(center);
        return this;
    }

//    public SeriesBuilder itemStyle(ItemStyle itemStyle){
//        this.itemStyle = itemStyle;
//        return this;
//    }

    public Series build(){
        Series series = new Series();
        series.setName(name);
        series.setType(type);
        series.setData(data);
        return series;
    }

    public LineBuilder endSeries(){
        this.lineBuilder.addSeries(build());
        return this.lineBuilder;
    }
}
