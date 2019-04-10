package com.roc.jframeworkecharts.model.radar;

import com.roc.jframeworkecharts.model.base.BasicTooltip;

public class Tooltip extends BasicTooltip {

    public static final class Builder {
        private String trigger = "";

        public Builder trigger(String trigger) {
            this.trigger = trigger;
            return this;
        }

        public Tooltip build() {
            Tooltip tooltip = new Tooltip();
            tooltip.setTrigger(trigger);
            return tooltip;
        }
    }
}
