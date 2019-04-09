package com.roc.jframeworkecharts.builder;

import com.roc.jframeworkecharts.model.Grid;

public class GridBuilder {
    private String left = "";
    private String right = "";
    private String bottom = "";
    private Boolean containLabel = true;

    private LineBuilder lineBuilder;

    public GridBuilder(LineBuilder lineBuilder){
        this.lineBuilder = lineBuilder;
    }

    public GridBuilder left(String left){
        this.left = left;
        return this;
    }

    public GridBuilder right(String right){
        this.right = right;
        return this;
    }

    public GridBuilder bottom(String bottom){
        this.bottom = bottom;
        return this;
    }

    public GridBuilder containLabel(Boolean containLabel){
        this.containLabel = containLabel;
        return this;
    }

    public Grid build(){
        Grid grid = new Grid();
        grid.setLeft(left);
        grid.setRight(right);
        grid.setBottom(bottom);
        grid.setContainLabel(containLabel);
        return grid;
    }

    public LineBuilder endGrid(){
        this.lineBuilder.setGrid(build());
        return this.lineBuilder;
    }
}
