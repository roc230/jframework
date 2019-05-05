package com.roc.jframework.crawler.novelweb;

import com.roc.jframework.basic.utils.IntegerUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.core.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class NovelInfoHelper {

    public static void saveAsExcel(List<NovelInfo> list, String filepath){
        XSSFWorkbook workbook = ExcelUtils.newWorkbook();
        XSSFSheet sheet = ExcelUtils.newSheet(workbook, null);
        Row head = sheet.createRow(0);
        head.createCell(0).setCellValue("名称");
        head.createCell(1).setCellValue("作者");
        head.createCell(2).setCellValue("题材");
        head.createCell(3).setCellValue("状态");
        head.createCell(4).setCellValue("点击量");
        head.createCell(5).setCellValue("字数");
        head.createCell(6).setCellValue("简介");
        head.createCell(7).setCellValue("地址");
        head.createCell(8).setCellValue("封面图");

        for(int i = 0; i < list.size(); i++){
            NovelInfo ni = list.get(i);
            Row r = sheet.createRow(i + 1);
            r.createCell(0).setCellValue(ni.getName());
            r.createCell(1).setCellValue(ni.getAuthor());
            r.createCell(2).setCellValue(ni.getCategory());
            r.createCell(3).setCellValue(ni.getStatus());

            r.createCell(4).setCellValue(StringUtils.isNullOrEmpty(ni.getClickCount()) ? 0: Integer.valueOf(ni.getClickCount()));
            r.createCell(5).setCellValue(ni.getWords());
            r.createCell(6).setCellValue(ni.getBrief());
            r.createCell(7).setCellValue(ni.getUrl());
            r.createCell(8).setCellValue(ni.getCoverImg());
        }

        ExcelUtils.saveWorkbook(workbook, filepath);
    }
}
