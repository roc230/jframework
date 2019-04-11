package com.roc.jframework.core;

import com.roc.jframework.core.utils.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class ExcelUtilsTest {

    @Test
    public void test(){
        XSSFWorkbook workbook = ExcelUtils.newWorkbook();
        XSSFSheet sheet = ExcelUtils.newSheet(workbook, "test");
        ExcelUtils.addRowToSheet(sheet, 0, new String[]{"序号","名称", "年龄"});
        ExcelUtils.addRowToSheet(sheet, 1, new Object[]{1,"roc", 18});
        ExcelUtils.saveWorkbook(workbook, "d:/aaa.xlsx");
    }
}
