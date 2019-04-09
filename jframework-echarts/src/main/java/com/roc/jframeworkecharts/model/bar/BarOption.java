package com.roc.jframeworkecharts.model.bar;

import java.io.Serializable;

public class BarOption implements Serializable {

    private Dataset dataset;

    private XAxis xAxis;

    private Series series;

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public XAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}
