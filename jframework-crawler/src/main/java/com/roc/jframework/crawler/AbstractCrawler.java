package com.roc.jframework.crawler;

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

public abstract class AbstractCrawler implements ICommonCrawler{

    protected Integer start = 0;
    protected Integer max = 1;
    protected Boolean append = false;
    protected Boolean login = false;
    protected String username;
    protected String password;

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
    public abstract void execute(String url);

    @Override
    public void saveAsJson(Novel novel, File file) {
        FileUtils.saveAsFile(JsonUtils.toString(novel), file, true);
    }

    @Override
    public void saveAsTxt(Novel novel, File file) {
        int count = 10;//每页保存多少章内容
        int pnum = novel.getChapters().size() / count + 1;
        if(novel.getChapters().size() % count == 0){
            pnum = novel.getChapters().size() / count;
        }

        for(int i = 0; i < pnum; i++){
            StringBuffer sb = new StringBuffer();
            //如果是最后一页
            if((i + 1) == pnum){
                for(int j = i*count; j < novel.getChapters().size(); j++){
                    Chapter chapter = novel.getChapters().get(j);
                    String name = chapter.getTitle();
                    sb.append("\r\n")
                            .append(name)
                            .append("\r\n")
                            .append("\r\n");
                    for(String p : chapter.getParagraphs()){
                        sb.append("\0\0\0\0\0\0\0\0")
                                .append(p)
                                .append("\r\n")
                                .append("\r\n");
                    }
                }
            }else{//非最后一页
                for(int j = i*count; j < (i+1)*count; j++){
                    Chapter chapter = novel.getChapters().get(j);
                    String name = chapter.getTitle();
                    sb.append("\r\n")
                            .append(name)
                            .append("\r\n")
                            .append("\r\n");
                    for(String p : chapter.getParagraphs()){
                        sb.append("\0\0\0\0\0\0\0\0")
                                .append(p)
                                .append("\r\n")
                                .append("\r\n");
                    }
                }
            }

            String filename = file.getAbsolutePath().replace(".txt", "");
            filename = filename + "-p" + i + ".txt";
            FileUtils.saveAsFile(sb.toString(), new File(filename), true);
        }
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
