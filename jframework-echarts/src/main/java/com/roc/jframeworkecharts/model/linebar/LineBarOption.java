package com.roc.jframeworkecharts.model.linebar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LineBarOption implements Serializable {
    private Tooltip tooltip;
    private Legend legend;
    private Boolean calculable = true;
    private List<Series> series;
    private List<XAxis> xAxis;
    private List<YAxis> yAxis;

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

    public Boolean getCalculable() {
        return calculable;
    }

    public void setCalculable(Boolean calculable) {
        this.calculable = calculable;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public void addSeries(Series... series){
        if(this.series == null){
            this.series = new ArrayList<>();
        }
        for(Series s : series){
            this.series.add(s);
        }
    }

    public List<XAxis> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<XAxis> xAxis) {
        this.xAxis = xAxis;
    }

    public void addXAxis(XAxis... xAxes){
        if(this.xAxis == null){
            this.xAxis = new ArrayList<>();
        }
        for(XAxis axis : xAxes){
            this.xAxis.add(axis);
        }
    }

    public List<YAxis> getyAxis() {
        return yAxis;
    }

    public void setyAxis(List<YAxis> yAxis) {
        this.yAxis = yAxis;
    }

    public void addYAxis(YAxis... yAxes){
        if(this.yAxis == null){
            this.yAxis = new ArrayList<>();
        }
        for(YAxis axis : yAxes){
            this.yAxis.add(axis);
        }
    }

    public static final class Builder{
        private LineBarOption option = new LineBarOption();

        public Legend.Builder legend(){
            return new Legend.Builder(this);
        }

        public Tooltip.Builder tooltip(){
            return new Tooltip.Builder(this);
        }

        public XAxis.Builder xAxis(){
            return new XAxis.Builder(this);
        }

        public YAxis.Builder yAxis(){
            return new YAxis.Builder(this);
        }

        public Series.Builder series(){
            return new Series.Builder(this);
        }

        public Builder calculable(Boolean calculable){
            this.option.setCalculable(calculable);
            return this;
        }

        public LineBarOption build(){
            return this.option;
        }

        public LineBarOption getOption() {
            return option;
        }
    }
}
