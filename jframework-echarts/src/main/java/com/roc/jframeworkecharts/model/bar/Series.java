package com.roc.jframeworkecharts.model.bar;

import com.roc.jframeworkecharts.model.base.BasicSeries;

public class Series extends BasicSeries {

    private MarkPoint markPoint;
    private MarkLine markLine;

    public MarkPoint getMarkPoint() {
        return markPoint;
    }

    public void setMarkPoint(MarkPoint markPoint) {
        this.markPoint = markPoint;
    }

    public MarkLine getMarkLine() {
        return markLine;
    }

    public void setMarkLine(MarkLine markLine) {
        this.markLine = markLine;
    }

    public static class Builder{
        private Series series = new Series();
        public Builder name(String name){
            this.series.setName(name);
            return this;
        }
        public Builder type(String type){
            this.series.setType(type);
            return this;
        }
        public Builder data(Object... data){
            this.series.addData(data);
            return this;
        }
        public Builder markPoint(MarkPoint markPoint){
            this.series.setMarkPoint(markPoint);
            return this;
        }
        public Builder markLine(MarkLine markLine){
            this.series.setMarkLine(markLine);
            return this;
        }
        public Series build(){
            return this.series;
        }
    }
}
