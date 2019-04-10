package com.roc.jframeworkecharts.model.radar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polar implements Serializable {

    private List<Indicator> indicator;

    public Polar(){}

    public Polar(List<Indicator> indicator){
        this.indicator = indicator;
    }

    public List<Indicator> getIndicator() {
        return indicator;
    }

    public void setIndicator(List<Indicator> indicator) {
        this.indicator = indicator;
    }

    public static class Builder{
        private Polar polar = new Polar();
        public Builder indicator(Indicator... indicators){
            this.polar.setIndicator(Arrays.asList(indicators));
            return this;
        }
        public Builder addIndicator(Indicator... indicators){
            if(this.polar.getIndicator() == null){
                this.polar.setIndicator(new ArrayList<>());
            }
            this.polar.getIndicator().addAll(Arrays.asList(indicators));
            return this;
        }
        public Polar build(){
            return this.polar;
        }
    }
}
