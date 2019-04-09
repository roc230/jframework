package com.roc.jframeworkecharts.model.line;

public class GridBuilder {

    private Grid grid = new Grid();
    private LineOptionBuilder lineOptionBuilder;

    public GridBuilder(LineOptionBuilder lineOptionBuilder){
        this.lineOptionBuilder = lineOptionBuilder;
    }

    public GridBuilder left(String left){
        this.grid.setLeft(left);
        return this;
    }

    public GridBuilder right(String right){
        this.grid.setRight(right);
        return this;
    }

    public GridBuilder bottom(String bottom){
        this.grid.setBottom(bottom);
        return this;
    }

    public GridBuilder containLabel(Boolean containLabel){
        this.grid.setContainLabel(containLabel);
        return this;
    }

    public Grid build(){
        return this.grid;
    }

    public LineOptionBuilder endGrid(){
        this.lineOptionBuilder.setGrid(this.build());
        return this.lineOptionBuilder;
    }
}
