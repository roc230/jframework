package com.roc.jframeworkecharts.model.funnel;

import com.roc.jframeworkecharts.model.base.BasicTooltip;

public class Tooltip extends BasicTooltip {

    private String formatter = "{a} <br/>{b} : {c}%";

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public static final class Builder{
        private FunnelOption.Builder optionBuilder;
        private Tooltip tooltip = new Tooltip();

        public Builder(FunnelOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
        }

        public Builder trigger(String trigger){
            this.tooltip.setTrigger(trigger);
            return this;
        }
        public Builder formatter(String formatter){
            this.tooltip.setFormatter(formatter);
            return this;
        }
        public FunnelOption.Builder endTooltip(){
            this.optionBuilder.setTooltip(this.tooltip);
            return this.optionBuilder;
        }
    }
}
