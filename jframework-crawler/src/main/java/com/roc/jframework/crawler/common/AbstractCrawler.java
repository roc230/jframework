package com.roc.jframework.crawler.common;

import com.roc.jframework.basic.utils.DateUtils;
import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.core.utils.ExcelUtils;
import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Novel;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.Date;

public abstract class AbstractCrawler implements ICommonCrawler {

    protected Integer start = 0;
    protected Integer max = 1;
    protected Boolean append = false;
    protected Boolean login = false;
    protected String username;
    protected String password;
    protected Boolean headless = false;

    @Override
    public AbstractCrawler start(Integer start){
        this.start = start;
        return this;
    }

    @Override
    public AbstractCrawler max(Integer max){
        this.max = max;
        return this;
    }

    @Override
    public AbstractCrawler append(Boolean append){
        this.append = append;
        return this;
    }

    @Override
    public ICommonCrawler login(Boolean login) {
        this.login = login;
        return this;
    }

    @Override
    public ICommonCrawler username(String username) {
        this.username = username;
        return this;
    }

    @Override
    public ICommonCrawler password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public ICommonCrawler headless(Boolean headless) {
        this.headless = headless;
        return this;
    }

    @Override
    public abstract void execute(String url);

    @Override
    public void saveAsJson(Novel novel, File file) {
        Tools.saveAsJson(novel, file);
    }

    @Override
    public void saveAsTxt(Novel novel, File file) {
        Tools.saveAsMultiTxt(novel, file, 10);
    }

    public void addExcelLog(Novel novel, String excelfilepath){
        XSSFWorkbook workbook = ExcelUtils.read(excelfilepath);
        XSSFSheet sheet = ExcelUtils.getSheet(workbook, 0);
        ExcelUtils.insertLastRowToSheet(sheet, new Object[]{sheet.getLastRowNum() + 1,
                novel.getName(),
                novel.getName(),
                DateUtils.getString(new Date(), "yyyy/MM/dd"),
                "1-"+novel.getChapters().size(),
                novel.getUrl()});
        ExcelUtils.saveWorkbook(workbook, excelfilepath);
    }
}
