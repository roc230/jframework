package com.roc.jframeworkecharts.model.line;

import java.util.List;

public class SeriesBuilder<T> {
    private LineOptionBuilder lineOptionBuilder;
    private Series series;

    public SeriesBuilder(LineOptionBuilder lineOptionBuilder){
        this.lineOptionBuilder = lineOptionBuilder;
        this.series = new Series();
    }

    public SeriesBuilder name(String name){
        this.series.setName(name);
        return this;
    }

    public SeriesBuilder type(String type){
        this.series.setType(type);
        return this;
    }

    public SeriesBuilder stack(String stack){
        this.series.setStack(stack);
        return this;
    }

    public SeriesBuilder addData(String data){
        this.series.addData(data);
        return this;
    }

    public SeriesBuilder setData(List<T> data){
        this.series.setData(data);
        return this;
    }

    public SeriesBuilder itemStyle(ItemStyle itemStyle){
        this.series.setItemStyle(itemStyle);
        return this;
    }

    public SeriesBuilder itemStyle(Boolean show, String position, String color){
        this.series.setItemStyle(show, position, color);
        return this;
    }

    public SeriesBuilder showData(Boolean show){
        this.series.getItemStyle().getNormal().getLabel().setShow(true);
        return this;
    }

    public Series build(){
        return this.series;
    }

    public LineOptionBuilder endSeries(){
        this.lineOptionBuilder.addSeries(this.build());
        return this.lineOptionBuilder;
    }
}
