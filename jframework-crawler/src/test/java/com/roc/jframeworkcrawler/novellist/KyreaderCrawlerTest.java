package com.roc.jframeworkcrawler.novellist;

import com.google.gson.reflect.TypeToken;
import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.core.utils.ExcelUtils;
import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.novellist.KyreaderCrawler;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.List;

public class KyreaderCrawlerTest {

    private static final String filename = "kyread-3";

    @Test
    public void test(){
        KyreaderCrawler crawler = new KyreaderCrawler();
        List<KyreaderCrawler.NovelInfo> list = crawler.getInfo("https://novel.1789a.com/index.php?s=/Update/get_cont_data/channel/%E5%85%A8%E9%83%A8/p/200.html", 100);
        String json = JsonUtils.toString(list);
        FileUtils.saveAsFile(json, filename + ".json", true);
    }

    @AfterClass
    public static void saveAsExcel(){
        String json = FileUtils.readAsString(filename + ".json");
        List<KyreaderCrawler.NovelInfo> list = JsonUtils.fromJson(json, new TypeToken<List<KyreaderCrawler.NovelInfo>>(){}.getType());

        XSSFWorkbook book = ExcelUtils.newWorkbook();
        XSSFSheet sheet = book.createSheet();
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("名称");
        header.createCell(1).setCellValue("题材");
        header.createCell(2).setCellValue("点击量");
        header.createCell(3).setCellValue("字数");
        header.createCell(4).setCellValue("地址");
        header.createCell(5).setCellValue("封面");

        for(int i = 0; i < list.size(); i++){
            KyreaderCrawler.NovelInfo ni = list.get(i);
            Row r = sheet.createRow(i + 1);
            r.createCell(0).setCellValue(ni.getName());
            r.createCell(1).setCellValue(ni.getCategory());
            r.createCell(2).setCellValue(ni.getClickCount());
            r.createCell(3).setCellValue(ni.getLength());
            r.createCell(4).setCellValue(ni.getUrl());
            r.createCell(5).setCellValue(ni.getCoverImg());
        }

        ExcelUtils.saveWorkbook(book, filename + ".xlsx");
    }
}
