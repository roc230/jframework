package com.roc.jframeworkecharts.model.bar;

import com.roc.jframeworkecharts.model.base.BasicTooltip;

public class Tooltip extends BasicTooltip {

    public static class Builder{
        private Tooltip tooltip;

        public Builder(){
            this.tooltip = new Tooltip();
        }

        public Builder trigger(String trigger){
            this.tooltip.setTrigger(trigger);
            return this;
        }

        public Tooltip build(){
            return this.tooltip;
        }
    }

}
