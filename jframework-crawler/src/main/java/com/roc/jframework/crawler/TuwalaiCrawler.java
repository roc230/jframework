package com.roc.jframework.crawler;

import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TuwalaiCrawler {


    /**
     * 入口方法
     */
    public void execute(){
        WebDriver driver = SeleniumUtils.chrome();
        //小说入口
        driver.get("http://www.tunwalai.com/story/243492/%E0%B8%9B%E0%B8%A5%E0%B8%B8%E0%B8%81%E0%B8%AA%E0%B8%A7%E0%B8%A3%E0%B8%A3%E0%B8%84%E0%B9%8C%E0%B8%AA%E0%B8%A2%E0%B8%9A%E0%B8%9B%E0%B8%90%E0%B8%9E%E0%B8%B5");

        //获取小说目录
        List<Chapter> chapters = getChapterUrls(driver);


        //逐章获取小说内容
        for(Chapter c : chapters){

            List<String> paragraphs = this.getChapterContent(driver, c.getUrl());
            if(paragraphs == null){
                break;
            }
            c.setContent(paragraphs);
        }
        //build Novel Object
        Novel n = new Novel();
        n.setName("Novel 1");
        n.setChapters(chapters);

        //save as json
        saveAsJson(n, "d:/novel1/novel1.json");

        //save as txt
        saveAsText(n, "d:/novel1/novel1.txt");


        //关闭driver
        driver.quit();
    }

    /**
     * Save Novel As Json
     * @param novel
     * @param filepath
     */
    public void saveAsJson(Novel novel, String filepath){
        String json = JsonUtils.toString(novel);
        FileUtils.saveAsFile(json, filepath, true);
    }

    /**
     * Save Novel As Txt.
     * @param novel
     * @param filepath
     */
    public void saveAsText(Novel novel, String filepath){
        StringBuffer sb = new StringBuffer();
        for(Chapter c : novel.getChapters()){
            sb.append(c.getTitle())
                    .append("\r\n")
                    .append("\r\n");
            //paragraph
            if(c.getContent() == null){
                break;
            }
            for(String p : c.getContent()){
                if(p == null){
                    break;
                }
                sb.append("\0\0\0\0\0\0\0\0")
                        .append(p)
                        .append("\r\n")
                        .append("\r\n");
            }
        }
        FileUtils.saveAsFile(sb.toString(), filepath, true);
    }

    /**
     * 获取章节的段落集合
     * @param driver
     * @param url
     * @return
     */
    public List<String> getChapterContent(WebDriver driver, String url){
        driver.get(url);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement detail = null;
        try {
            detail = driver.findElement(By.id("story-detail"));
            if(detail == null){
                return null;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        List<String> paragraphContent = new ArrayList<>();
        try {
            List<WebElement> plist = detail.findElements(By.tagName("p"));
            for(WebElement p:plist){
                String text = p.getText();
                paragraphContent.add(text);
            }

            return paragraphContent;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取章节入口地址集合
     * @param driver
     * @return
     */
    public List<Chapter> getChapterUrls(WebDriver driver){
        WebElement table = driver.findElement(By.className("main-list-chapter"));
        if(table == null){
            return null;
        }


        List<WebElement> alist = table.findElements(By.cssSelector("#tbody tr a[target]"));
        if(ListUtils.isNullOrEmpty(alist)){
            return null;
        }

//        SeleniumUtils.printText(alist);


        List<Chapter> chapters = new ArrayList<>();
        for(WebElement a : alist){
            String href = a.getAttribute("href");
            String title = a.getAttribute("title");
            Chapter c = new Chapter();
            c.setTitle(title);
            c.setUrl(href);
            chapters.add(c);
        }
        return chapters;
    }

    class Novel{
        private String name;
        private List<Chapter> chapters;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Chapter> getChapters() {
            return chapters;
        }

        public void setChapters(List<Chapter> chapters) {
            this.chapters = chapters;
        }
    }

    class Chapter{
        private String title;
        private String url;
        private List<String> content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }
    }
}
