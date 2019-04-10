package com.roc.jframeworkecharts.model.bar;

import com.roc.jframeworkecharts.model.base.BasicToolbox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BarOption implements Serializable {

    private Title title;
    private Tooltip tooltip;
    private Legend legend;

    private List<XAxis> xAxis;
    private List<YAxis> yAxis;

    private List<Series> series;

    private BasicToolbox toolbox;

    private Boolean calculable = true;

    public Title getTitle() {
        return title;
    }

    public BarOption setTitle(Title title) {
        this.title = title;
        return this;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public BarOption setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public Legend getLegend() {
        return legend;
    }

    public BarOption setLegend(Legend legend) {
        this.legend = legend;
        return this;
    }

    public List<XAxis> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<XAxis> xAxis) {
        this.xAxis = xAxis;
    }

    public List<YAxis> getyAxis() {
        return yAxis;
    }

    public void setyAxis(List<YAxis> yAxis) {
        this.yAxis = yAxis;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public BarOption addSeries(Series... series){
        if(this.series == null){
            this.series = new ArrayList<>();
        }
        this.series.addAll(Arrays.asList(series));
        return this;
    }

    public BarOption addXAxis(XAxis... xAxis){
        if(this.xAxis == null){
            this.xAxis = new ArrayList<>();
        }
        this.xAxis.addAll(Arrays.asList(xAxis));
        return this;
    }

    public BarOption addYAxis(YAxis... yAxis){
        if(this.yAxis == null){
            this.yAxis = new ArrayList<>();
        }
        this.yAxis.addAll(Arrays.asList(yAxis));
        return this;
    }

    public Boolean getCalculable() {
        return calculable;
    }

    public BarOption setCalculable(Boolean calculable) {
        this.calculable = calculable;
        return this;
    }


    public BasicToolbox getToolbox() {
        return toolbox;
    }

    public BarOption setToolbox(BasicToolbox toolbox) {
        this.toolbox = toolbox;
        return this;
    }
}
