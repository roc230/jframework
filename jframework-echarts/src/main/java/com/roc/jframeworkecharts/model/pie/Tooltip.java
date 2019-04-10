package com.roc.jframeworkecharts.model.pie;

import com.roc.jframeworkecharts.model.base.BasicTooltip;

public class Tooltip extends BasicTooltip {
    private String formatter = "";

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public static final class Builder {
        private String trigger = "";
        private String formatter = "";

        public Builder trigger(String trigger) {
            this.trigger = trigger;
            return this;
        }

        public Builder formatter(String formatter) {
            this.formatter = formatter;
            return this;
        }

        public Tooltip build() {
            Tooltip tooltip = new Tooltip();
            tooltip.setTrigger(trigger);
            tooltip.setFormatter(formatter);
            return tooltip;
        }
    }
}
