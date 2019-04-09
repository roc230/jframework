package com.roc.jframeworkecharts.model.line;

import java.util.List;

public class LegendBuilder {
    private LineOptionBuilder lineOptionBuilder;
    private Legend legend;

    public LegendBuilder(LineOptionBuilder lineOptionBuilder){
        this.lineOptionBuilder = lineOptionBuilder;
        this.legend = new Legend();
    }

    public LegendBuilder addData(String data){
        legend.addData(data);
        return this;
    }

    public LegendBuilder setData(List<String> data){
        legend.setData(data);
        return this;
    }

    public Legend build(){
        return this.legend;
    }

    public LineOptionBuilder endLegend(){
        this.lineOptionBuilder.setLegend(this.build());
        return this.lineOptionBuilder;
    }
}
