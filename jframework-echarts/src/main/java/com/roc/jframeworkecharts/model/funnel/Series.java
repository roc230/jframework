package com.roc.jframeworkecharts.model.funnel;

import com.roc.jframework.basic.ext.NameValue;
import com.roc.jframeworkecharts.model.base.BasicItemStyle;
import com.roc.jframeworkecharts.model.base.BasicNormal;
import com.roc.jframeworkecharts.model.base.BasicSeries;

import java.util.ArrayList;
import java.util.List;

public class Series extends BasicSeries {
    private String width = "";

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public static final class Builder{
        private FunnelOption.Builder optionBuilder;
        private Series series = new Series();
        public Builder(FunnelOption.Builder optionBuilder){
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
        public Builder width(String width){
            this.series.setWidth(width);
            return this;
        }
        public Builder data(List<NameValue> data){
            this.series.setData(data);
            return this;
        }
        public Builder addData(String name, Object value){
            if(this.series.getData() == null){
                this.series.setData(new ArrayList());
            }
            this.series.getData().add(new NameValue<>(name, value));
            return this;
        }

        public Builder addData(NameValue... data){
            if(this.series.getData() == null){
                this.series.setData(new ArrayList());
            }
            for(NameValue nv:data){
                this.series.getData().add(nv);
            }
            return this;
        }

        public Builder showData(Boolean show){
            this.series.setItemStyle(new BasicItemStyle(new BasicNormal(new Label(true, "inside"))));
            return this;
        }

        public FunnelOption.Builder endSeries(){
            this.optionBuilder.addSeries(this.series);
            return this.optionBuilder;
        }

    }
}
