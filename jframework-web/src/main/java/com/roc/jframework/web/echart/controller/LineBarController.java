package com.roc.jframework.web.echart.controller;

import com.roc.jframework.basic.utils.DoubleUtils;
import com.roc.jframework.basic.utils.IntegerUtils;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.core.utils.ExcelUtils;
import com.roc.jframeworkecharts.model.linebar.LineBarOption;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "/echart")
@Controller
public class LineBarController {

    @RequestMapping("/linebar")
    @ResponseBody
    public LineBarOption linebar(){
        /*LineOption option = LineOptionBuilder.create()
                .title().text("test").endTitle()
                .tooltip().trigger("axis").endTooltip()
                .legend().addData("蒸发量","降水量","平均温度").endLegend()
                .xAxis().addData("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月").type("category").endXAxis()
                .yAxis().type("value").name("水量").axisLabelFormatter("{value}ml").endYAxis()
                .yAxis().type("value").name("温度").axisLabelFormatter("{value}C").endYAxis()
                .series().name("蒸发量").type("bar").addData(2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3).endSeries()
                .series().name("降水量").type("bar").addData(2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3).endSeries()
                .series().name("平均温度").type("line").yAxisIndex(1).addData(2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2).endSeries()
                .build();
        return option;*/
        LineBarOption option = new LineBarOption.Builder()
                .tooltip().trigger("axis").endTooltip()
                .legend().addData("蒸发量","降水量","平均温度").endLegend()
                .xAxis().type("category").data("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月").endXAxis()
                .yAxis().type("value").name("水量").formatter("{value} ml").endYAxis()
                .yAxis().type("value").name("温度").formatter("{value} C").endYAxis()
                .series().type("bar").name("蒸发量").data(2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3).showData(true).endSeries()
                .series().type("bar").name("降水量").data(2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3).showData(true).endSeries()
                .series().type("line").name("平均温度").yAxisIndex(1).data(2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2).showData(true).endSeries()
                .build();
        return option;

    }

    @RequestMapping(value = "/tui")
    @ResponseBody
    public LineBarOption tui(){

        XSSFWorkbook book = ExcelUtils.read("d:/android_整体趋势_20190317_20190416.xlsx");
        XSSFSheet sheet = ExcelUtils.getSheet(book, 0);
        List<String> days = ExcelUtils.getDateColumn(sheet, 0, "yyyy/MM/dd").get("日期");
        days = ListUtils.reverse(days);

        List<Double> newusers = ListUtils.reverse(ExcelUtils.getDoubleColumn(sheet, 1).get("新增用户"));

        List<Double> activeusers = ListUtils.reverse(ExcelUtils.getDoubleColumn(sheet, 2).get("活跃用户"));

        List<Double> existsRatio = ListUtils.reverse(ExcelUtils.getDoubleColumn(sheet, 5).get("次日留存率"));
        existsRatio = DoubleUtils.multiply(existsRatio, 100D, 2);

        return new LineBarOption.Builder()
                .tooltip().trigger("axis").endTooltip()
                .legend().addData("新增用户","活跃用户","次日留存率").endLegend()
                .xAxis().type("category").data(days.toArray(new String[0])).endXAxis()
                .yAxis().type("value").name("用户量").formatter("{value}人").endYAxis()
                .yAxis().type("value").name("比率").formatter("{value}%").endYAxis()
                .series().type("line").name("新增用户").showData(false).data(DoubleUtils.toInteger(newusers).toArray(new Integer[0])).endSeries()
                .series().type("line").name("活跃用户").showData(false).data(DoubleUtils.toInteger(activeusers).toArray(new Integer[0])).endSeries()
                .series().type("line").name("次日留存率").showData(false).data(existsRatio.toArray(new Double[0])).yAxisIndex(1).endSeries()
                .build();
    }

}
