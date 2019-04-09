package com.roc.jframeworkecharts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Option implements Serializable {

    private Tooltip tooltip;

    private Legend legend;

    private List<XAxis> xAxis;

    private List<YAxis> yAxis;

    private List<Series> series;

    private Grid grid;

    private Title title;

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public List<XAxis> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<XAxis> xAxis) {
        this.xAxis = xAxis;
    }

    public void addXAxis(XAxis xAxis){
        if(this.xAxis == null){
            this.xAxis = new ArrayList<>();
        }
        this.xAxis.add(xAxis);
    }

    public List<YAxis> getyAxis() {
        return yAxis;
    }

    public void setyAxis(List<YAxis> yAxis) {
        this.yAxis = yAxis;
    }

    public void addYAxis(YAxis yAxis){
        if(this.yAxis == null){
            this.yAxis = new ArrayList<>();
        }
        this.yAxis.add(yAxis);
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public void addSeries(Series series){
        if(this.series == null){
            this.series = new ArrayList<>();
        }
        this.series.add(series);
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
