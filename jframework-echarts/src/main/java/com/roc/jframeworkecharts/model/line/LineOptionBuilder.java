package com.roc.jframeworkecharts.model.line;

import java.util.ArrayList;
import java.util.List;

public class LineOptionBuilder {

    private Title title;
    private Tooltip tooltip;
    private Legend legend;
    private Grid grid;
    private XAxis xAxis;
    private YAxis yAxis;
    private List<Series> series;

    public static LineOptionBuilder create(){
        return new LineOptionBuilder();
    }

    public TooltipBuilder tooltip(){
        return new TooltipBuilder(this);
    }
    public GridBuilder grid(){
        return new GridBuilder(this);
    }
    public TitleBuilder title(){
        return new TitleBuilder(this);
    }
    public TooltipBuilder tootip(){
        return new TooltipBuilder(this);
    }
    public LegendBuilder legend(){
        return new LegendBuilder(this);
    }
    public XAxisBuilder xAxis(){
        return new XAxisBuilder(this);
    }
    public YAxisBuilder yAxis(){
        return new YAxisBuilder(this);
    }
    public SeriesBuilder series(){
        return new SeriesBuilder(this);
    }

    public LineOption build(){
        LineOption option = new LineOption();
        option.setGrid(grid);
        option.setLegend(legend);
        option.setSeries(series);
        option.setTitle(title);
        option.setTooltip(tooltip);
        option.setxAxis(xAxis);
        option.setyAxis(yAxis);
        return option;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setxAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public void setyAxis(YAxis yAxis) {
        this.yAxis = yAxis;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public LineOptionBuilder addSeries(Series series){
        if(this.series == null){
            this.series = new ArrayList<>();
        }
        this.series.add(series);
        return this;
    }
}
