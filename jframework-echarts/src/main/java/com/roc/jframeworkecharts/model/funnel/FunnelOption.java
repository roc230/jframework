package com.roc.jframeworkecharts.model.funnel;

import com.roc.jframeworkecharts.model.base.BasicItemStyle;
import com.roc.jframeworkecharts.model.base.BasicLabel;
import com.roc.jframeworkecharts.model.base.BasicNormal;

import java.util.ArrayList;
import java.util.List;

public class FunnelOption {
    private Title title;
    private Tooltip tooltip;
    private Legend legend;
    private Boolean calculable = true;
    private List<Series> series;


    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

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

    public static final class Builder{
        private Title title;
        private Tooltip tooltip;
        private Legend legend;
        private Boolean calculable = true;
        private List<Series> series;

        public Title.Builder title(){
            return new Title.Builder(this);
        }

        public Tooltip.Builder tooltip(){
            return new Tooltip.Builder(this);
        }

        public Legend.Builder legend(){
            return new Legend.Builder(this);
        }

        public Builder calculable(Boolean calculable){
            this.calculable = calculable;
            return this;
        }

        public Series.Builder series(){
            return new Series.Builder(this);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public void setTooltip(Tooltip tooltip) {
            this.tooltip = tooltip;
        }

        public void setLegend(Legend legend) {
            this.legend = legend;
        }

        public void setCalculable(Boolean calculable) {
            this.calculable = calculable;
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

        public FunnelOption build(){
            FunnelOption option  = new FunnelOption();
            option.setTitle(this.title);
            option.setTooltip(this.tooltip);
            option.setCalculable(this.calculable);
            option.setLegend(this.legend);
            option.setSeries(this.series);
            return option;
        }
    }
}
