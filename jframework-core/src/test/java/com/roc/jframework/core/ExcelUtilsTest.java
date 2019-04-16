package com.roc.jframework.core;

import com.roc.jframework.basic.utils.DoubleUtils;
import com.roc.jframework.basic.utils.IntegerUtils;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.core.utils.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ExcelUtilsTest {

    @Test
    public void test_saveWorkbook(){
        XSSFWorkbook workbook = ExcelUtils.newWorkbook();
        XSSFSheet sheet = ExcelUtils.newSheet(workbook, "test");
        ExcelUtils.addRowToSheet(sheet, 0, new String[]{"序号","名称", "年龄"});
        ExcelUtils.addRowToSheet(sheet, 1, new Object[]{1,"roc", 18});
        ExcelUtils.saveWorkbook(workbook, "d:/aaa.xlsx");
    }

    @Test
    public void test_getCells(){
        XSSFWorkbook book = ExcelUtils.read("d:/android_整体趋势_20190316_20190415.xlsx");
        XSSFSheet sheet = ExcelUtils.getSheet(book, 0);
        Map<String,List<String>> map = ExcelUtils.getDateColumn(sheet, 0, "yyyy/MM/dd");
        for(Map.Entry<String,List<String>> entry : map.entrySet()){
            System.out.println(entry.getKey());
            ListUtils.printList(entry.getValue());
        }
    }

    @Test
    public void test_getAllRows(){
        XSSFWorkbook book = ExcelUtils.read("d:/android_整体趋势_20190316_20190415.xlsx");
        XSSFSheet sheet = ExcelUtils.getSheet(book, 0);
        Map map = ExcelUtils.getAllRows(sheet);
        System.out.println("end");
    }

    @Test
    public void test_getDoubleColumns(){
        XSSFWorkbook book = ExcelUtils.read("d:/android_整体趋势_20190316_20190415.xlsx");
        XSSFSheet sheet = ExcelUtils.getSheet(book, 0);
        Map<String, List<Double>> map = ExcelUtils.getDoubleColumn(sheet, 5);
        for(Map.Entry<String,List<Double>> entry : map.entrySet()){
            System.out.println(entry.getKey());
            List<Double> ll = DoubleUtils.multiply(entry.getValue(), 100D, 2);
            ListUtils.printList(ll);
        }
    }
}
