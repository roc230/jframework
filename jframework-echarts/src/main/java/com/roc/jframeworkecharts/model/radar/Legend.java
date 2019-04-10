package com.roc.jframeworkecharts.model.radar;

import com.roc.jframeworkecharts.model.base.BasicLegend;

import java.util.Arrays;

public class Legend extends BasicLegend {
    private String orient = "";
    private String x = "";
    private String y = "";

    public String getOrient() {
        return orient;
    }

    public void setOrient(String orient) {
        this.orient = orient;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public static class Builder{
        private Legend legend = new Legend();
        public Builder x(String x){
            this.legend.setX(x);
            return this;
        }
        public Builder y(String y){
            this.legend.setY(y);
            return this;
        }
        public Builder orient(String orient){
            this.legend.setOrient(orient);
            return this;
        }
        public Builder data(String... data){
            this.legend.setData(Arrays.asList(data));
            return this;
        }
        public Legend build(){
            return this.legend;
        }
    }

}
