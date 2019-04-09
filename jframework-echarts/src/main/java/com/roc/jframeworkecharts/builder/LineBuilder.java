package com.roc.jframeworkecharts.builder;

import com.roc.jframeworkecharts.model.*;

import java.util.ArrayList;
import java.util.List;

public class LineBuilder {

//    private TooltipBuilder tooltipBuilder;
//    private LegendBuilder legendBuilder;
//    private XAxisBuilder xAxisBuilder;
//    private YAxisBuilder yAxisBuilder;
//    private SeriesBuilder seriesBuilder;
//    private GridBuilder gridBuilder;
//    private TitleBuilder titleBuilder;
    private Tooltip tooltip;
    private Legend legend;
    private List<XAxis> xAxis;
    private List<YAxis> yAxis;
    private List<Series> series;
    private Grid grid;
    private Title title;

    public static LineBuilder create(){
        return new LineBuilder();
    }

    public Option build(){
        Option option = new Option();
        option.setTooltip(tooltip);
        option.setLegend(legend);
        option.setxAxis(xAxis);
        option.setyAxis(yAxis);
        option.setSeries(series);
        option.setGrid(grid);
        option.setTitle(title);
//        option.setTooltip(tooltipBuilder.build());
//        option.setLegend(legendBuilder.build());
//        option.addXAxis(xAxisBuilder.build());
//        option.addYAxis(yAxisBuilder.build());
//        option.addSeries(seriesBuilder.build());
//        option.setGrid(gridBuilder.build());
//        option.setTitle(titleBuilder.build());
        return option;
    }

    public TooltipBuilder tooltip(){
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

    public GridBuilder grid(){
        return new GridBuilder(this);
    }

    public TitleBuilder title(){
        return new TitleBuilder(this);
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public void setxAxis(List<XAxis> xAxis) {
        this.xAxis = xAxis;
    }

    public void addXAxis(XAxis xAxis){
        if(this.xAxis == null){
            this.xAxis = new ArrayList<>();
        }
        this.xAxis.add(xAxis);
    }

    public void setyAxis(List<YAxis> yAxis) {
        this.yAxis = yAxis;
    }

    public void addYAxis(YAxis yAxis){
        if(this.yAxis == null){
            this.yAxis = new ArrayList<>();
        }
        this.yAxis.add(yAxis);
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public void addSeries(Series series){
        if(this.series == null){
            this.series = new ArrayList<>();
        }
        this.series.add(series);
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    /* public void setTooltipBuilder(TooltipBuilder tooltipBuilder) {
        this.tooltipBuilder = tooltipBuilder;
    }

    public void setLegendBuilder(LegendBuilder legendBuilder) {
        this.legendBuilder = legendBuilder;
    }

    public void setxAxisBuilder(XAxisBuilder xAxisBuilder) {
        this.xAxisBuilder = xAxisBuilder;
    }

    public void setyAxisBuilder(YAxisBuilder yAxisBuilder) {
        this.yAxisBuilder = yAxisBuilder;
    }

    public void setSeriesBuilder(SeriesBuilder seriesBuilder) {
        this.seriesBuilder = seriesBuilder;
    }

    public void setGridBuilder(GridBuilder gridBuilder) {
        this.gridBuilder = gridBuilder;
    }

    public void setTitleBuilder(TitleBuilder titleBuilder) {
        this.titleBuilder = titleBuilder;
    }*/
}
