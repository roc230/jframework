package com.roc.jframeworkecharts.model.bar;

import com.roc.jframework.basic.ext.HashMapExt;
import com.roc.jframeworkecharts.model.base.BasicSeries;

import java.util.Map;

public class Series extends BasicSeries {

    private MarkPoint markPoint = new MarkPoint();
    private MarkLine markLine = new MarkLine();

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
        private BarOption.Builder optionBuilder;

        public Builder(BarOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
        }

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
        public Builder markPoint(HashMapExt map){
            this.series.getMarkPoint().addData(map);
            return this;
        }
        public Builder markLine(MarkLine markLine){
            this.series.setMarkLine(markLine);
            return this;
        }
        public Builder markLine(String type, String name){
            HashMapExt map = new HashMapExt()
                    .putE("type", type)
                    .putE("name", name);
            this.series.getMarkLine().addData(map);
            return this;
        }
        public Builder showData(Boolean show){
            this.series.getItemStyle().getNormal().getLabel().setShow(show);
            return this;
        }

        public BarOption.Builder endSeries(){
            this.optionBuilder.getOption().addSeries(this.series);
            return this.optionBuilder;
        }

    }
}
