package com.roc.jframeworkecharts.model.radar;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class RadarOption implements Serializable {
    private Title title;
    private Tooltip tooltip;
    private Legend legend;
    private List<Polar> polar;

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

    public List<Polar> getPolar() {
        return polar;
    }

    public void setPolar(List<Polar> polar) {
        this.polar = polar;
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

    public static class Builder{
        RadarOption option = new RadarOption();
        public Builder title(Title title){
            this.option.setTitle(title);
            return this;
        }
        public Builder legend(Legend legend){
            this.option.setLegend(legend);
            return this;
        }
        public Builder tooltip(Tooltip tooltip){
            this.option.setTooltip(tooltip);
            return this;
        }
        public Builder calculable(Boolean calculable){
            this.option.setCalculable(calculable);
            return this;
        }
        public Builder polar(Polar... polar){
            this.option.setPolar(Arrays.asList(polar));
            return this;
        }
        public Builder series(Series... series){
            this.option.setSeries(Arrays.asList(series));
            return this;
        }
        public RadarOption build(){
            return this.option;
        }
    }
}
