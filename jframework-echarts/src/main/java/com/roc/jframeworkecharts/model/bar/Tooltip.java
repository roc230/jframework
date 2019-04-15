package com.roc.jframeworkecharts.model.bar;

import com.roc.jframeworkecharts.model.base.BasicTooltip;

public class Tooltip extends BasicTooltip {

    public static class Builder{
        private Tooltip tooltip;
        private BarOption.Builder optionBuilder;

        public Builder(){
            this.tooltip = new Tooltip();
        }

        public Builder(BarOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
            this.tooltip = new Tooltip();
        }

        public Builder trigger(String trigger){
            this.tooltip.setTrigger(trigger);
            return this;
        }

        public BarOption.Builder endTooltip(){
            this.optionBuilder.getOption().setTooltip(this.tooltip);
            return this.optionBuilder;
        }

    }

}
