package com.roc.jframeworkecharts;

import com.roc.jframeworkecharts.model.bar.BarOption;
import com.roc.jframeworkecharts.model.funnel.FunnelOption;
import com.roc.jframeworkecharts.model.line.LineOptionBuilder;
import com.roc.jframeworkecharts.model.linebar.LineBarOption;
import com.roc.jframeworkecharts.model.pie.PieOption;
import com.roc.jframeworkecharts.model.radar.RadarOption;

public class Echarts {

    /**
     * 拆线图
     * @return
     */
    public static LineOptionBuilder lineOptionBuilder(){
        return LineOptionBuilder.create();
    }

    /**
     * 条形图
     * @return
     */
    public static BarOption.Builder barOptionBuilder(){
        return new BarOption.Builder();
    }

    /**
     * 拆线条形图混合
     * @return
     */
    public static LineBarOption.Builder lineBarOptionBuilder(){
        return new LineBarOption.Builder();
    }

    /**
     * 饼图
     * @return
     */
    public static PieOption.Builder pieOptionBuilder(){
        return new PieOption.Builder();
    }

    /**
     * 雷达图
     * @return
     */
    public static RadarOption.Builder radarOptionBuilder(){
        return new RadarOption.Builder();
    }

    /**
     * 漏斗图
     * @return
     */
    public static FunnelOption.Builder funnelOptionBuilder(){
        return new FunnelOption.Builder();
    }

}
