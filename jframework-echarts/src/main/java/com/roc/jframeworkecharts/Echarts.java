package com.roc.jframeworkecharts;

import com.roc.jframeworkecharts.builder.LineBuilder;

public class Echarts {

    public static LineBuilder lineBuilder(){
        return LineBuilder.create();
    }
}
