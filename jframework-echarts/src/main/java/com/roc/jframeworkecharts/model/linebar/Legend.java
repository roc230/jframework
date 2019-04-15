package com.roc.jframeworkecharts.model.linebar;

import com.roc.jframeworkecharts.model.base.BasicLegend;

import java.util.Arrays;

public class Legend extends BasicLegend {

    public static final class Builder{
        private LineBarOption.Builder optionBuilder;
        private Legend legend = new Legend();
        public Builder(LineBarOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
        }
        public Builder addData(String... data){
            this.legend.setData(Arrays.asList(data));
            return this;
        }
        public LineBarOption.Builder endLegend(){
            this.optionBuilder.getOption().setLegend(this.legend);
            return this.optionBuilder;
        }
    }
}
