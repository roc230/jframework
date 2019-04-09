package com.roc.jframeworkecharts.model.line;

public class TooltipBuilder {

    private LineOptionBuilder lineOptionBuilder;

    private Tooltip tooltip;

    public TooltipBuilder(LineOptionBuilder lineOptionBuilder){
        this.lineOptionBuilder = lineOptionBuilder;
        this.tooltip = new Tooltip();
    }

    public TooltipBuilder trigger(String trigger){
        this.tooltip.setTrigger(trigger);
        return this;
    }

    public Tooltip build(){
        return this.tooltip;
    }

    public LineOptionBuilder endTooltip(){
        this.lineOptionBuilder.setTooltip(this.build());
        return this.lineOptionBuilder;
    }
}
