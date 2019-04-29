package com.roc.jframeworkweb.webcrawler.service;

import com.roc.jframework.core.utils.ExcelUtils;
import com.roc.jframework.web.JframeworkWebApplication;
import com.roc.jframework.web.webcrawler.entity.Status;
import com.roc.jframework.web.webcrawler.entity.WebNovel;
import com.roc.jframework.web.webcrawler.service.IWebNovelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JframeworkWebApplication.class})
public class PingzhiwenxueExcelToDb {

    @Autowired
    private IWebNovelService webNovelService;

    @Test
    public void test(){
        XSSFWorkbook workbook = ExcelUtils.read("d:/pingzhiwenxue.xlsx");
        XSSFSheet sheet = ExcelUtils.getSheet(workbook, 0);
        for(Iterator<Row> iter = sheet.rowIterator(); iter.hasNext(); ){
            Row row = iter.next();
            if(row.getRowNum() == 0){
                continue;
            }
            String name = row.getCell(0).getStringCellValue();
            String author = row.getCell(1).getStringCellValue();
            String category = row.getCell(2).getStringCellValue();
            String status = row.getCell(3).getStringCellValue();
            Integer clickCount = new Double(row.getCell(4).getNumericCellValue()).intValue();
            Integer words = new Double(row.getCell(5).getNumericCellValue()).intValue() * 10000;
            String brief = row.getCell(6).getStringCellValue();
            String url = row.getCell(7).getStringCellValue();

            WebNovel novel = new WebNovel.Builder()
                    .brief(brief)
                    .author(author)
                    .alias("")
                    .clickCount(clickCount)
                    .name(name)
                    .status(status.equals("已完结") ? Status.FINISHED : Status.ON_PROGRESS)
                    .recordTime(new Date())
                    .url(url)
                    .words(words)
                    .enable(true)
                    .build();
            this.webNovelService.save(novel, new String[]{"1"}, "1");
        }
    }
}
