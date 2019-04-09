package com.roc.jframeworkecharts.builder;

import com.roc.jframeworkecharts.model.Tooltip;

public class TooltipBuilder {

    private LineBuilder lineBuilder;

    private Boolean show = true;
    private String trigger = "";
    private String formatter = "";

    public TooltipBuilder(LineBuilder lineBuilder){
        this.lineBuilder = lineBuilder;
    }

    public Tooltip build(){
        Tooltip tooltip = new Tooltip();
        tooltip.setShow(this.show);
        tooltip.setTrigger(trigger);
        tooltip.setFormatter(formatter);
        return tooltip;
    }

    public TooltipBuilder show(Boolean show){
        this.show = show;
        return this;
    }

    public TooltipBuilder trigger(String trigger){
        this.trigger = trigger;
        return this;
    }

    public TooltipBuilder formatter(String formatter){
        this.formatter = formatter;
        return this;
    }

    public LineBuilder endTooptip(){
        this.lineBuilder.setTooltip(build());
        return this.lineBuilder;
    }
}
