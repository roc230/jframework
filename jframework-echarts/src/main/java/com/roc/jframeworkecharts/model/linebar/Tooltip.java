package com.roc.jframeworkecharts.model.linebar;

import com.roc.jframeworkecharts.model.base.BasicTooltip;

public class Tooltip extends BasicTooltip {
    private String trigger = "axis";

    @Override
    public String getTrigger() {
        return trigger;
    }

    @Override
    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public static final class Builder{
        private Tooltip tooltip = new Tooltip();
        private LineBarOption.Builder optionBuilder;

        public Builder(LineBarOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
        }

        public Builder trigger(String trigger){
            this.tooltip.setTrigger(trigger);
            return this;
        }

        public LineBarOption.Builder endTooltip(){
            this.optionBuilder.getOption().setTooltip(this.tooltip);
            return this.optionBuilder;
        }
    }
}
