package com.roc.jframework.crawler;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Directory;
import com.roc.jframework.crawler.entity.Novel;
import com.roc.jframework.crawler.selenium.DriverPath;
import com.roc.jframework.crawler.selenium.WebDriverBuilder;
import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TuwalaiCommonCrawler {

    private Integer start = 0;
    private Integer max = 1;
    private Boolean append = false;

    public static TuwalaiCommonCrawler create(){
        return new TuwalaiCommonCrawler();
    }

    /**
     * 设置开始章节，从0开始算
     * @param start
     * @return
     */
    public TuwalaiCommonCrawler start(Integer start){
        this.start = start;
        return this;
    }

    /**
     * 设置抓取的章节数量
     * @param max
     * @return
     */
    public TuwalaiCommonCrawler max(Integer max){
        this.max = max;
        return this;
    }

    /**
     * 设置是否以追加的方式保存内容
     * @param append
     * @return
     */
    public TuwalaiCommonCrawler append(Boolean append){
        this.append = append;
        return this;
    }

    /**
     * 执行抓取
     * @param url
     */
    public void execute(String url){
        WebDriver driver = WebDriverBuilder.create()
                .driverPath(DriverPath.CHROME_DRIVER_PATH)
                .headless(false)
                .loadImg(false)
                .userAgent(UserAgent.CHROME)
                .buildChrome();
        driver.get(url);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String novelName = null;
        try {
            WebElement p = driver.findElement(By.cssSelector("#story-content p[title]"));
            novelName = p.getAttribute("title");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Novel novel = new Novel();
        novel.setName(novelName);
        novel.setUrl(url);

        List<Directory> directories = getDirectories(driver);

        List<Chapter> chapters = getChapters(driver, directories);

        novel.setDirectories(directories);
        novel.setChapters(chapters);

        String json = JsonUtils.toString(novel);
        FileUtils.saveAsFile(json, "d:/novel1/" + novel.getName() + ".json", true);

        saveAsTxt(novel);

        driver.quit();
    }

    public void saveAsTxt(Novel novel){
        StringBuffer sb = new StringBuffer();
        for(Chapter chapter : novel.getChapters()){
            //章节标题
            String name = chapter.getTitle();
            sb.append(name).append("\r\n").append("\r\n");
            //内容段落
            List<String> paragraphs = chapter.getParagraphs();
            for(String p : paragraphs){
                sb.append("\0\0\0\0\0\0\0\0")
                        .append(p)
                        .append("\r\n")
                        .append("\r\n");
            }
        }
        FileUtils.saveAsFile(sb.toString(), "d:/novel1/" + novel.getName() + ".txt", !append);
    }

    /**
     * 获取章节内容
     * @param driver
     * @param directories
     * @return
     */
    public List<Chapter> getChapters(WebDriver driver, List<Directory> directories){
        if(start < 0 || max > directories.size()){
            System.out.println("参数错误");
            return null;
        }
        List<Chapter> chapters = ListUtils.newArrayList();

        for(int i = start; i < max; i++){
            Directory dir = directories.get(i);
            driver.get(dir.getUrl());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<WebElement> plist = SeleniumUtils.findElements(driver, By.cssSelector("#story-detail p"));
            if(ListUtils.isNullOrEmpty(plist)){
                return null;
            }
            List<String> paragraphs = ListUtils.newArrayList();
            for(WebElement p : plist){

                String ptxt = p.getText();
                ptxt = ptxt.trim();
//                if(ptxt.startsWith("\"")){
//                    ptxt = ptxt.substring(1);
//                }
//                if(ptxt.endsWith("\"")){
//                    ptxt = ptxt.substring(0, ptxt.length() -1);
//                }

                WebElement em = SeleniumUtils.findElement(p, By.cssSelector("em"));
                if(em != null){
                    String txt = em.getText();
                    ptxt += txt;
                }

                paragraphs.add(ptxt);
            }

            Chapter chapter = new Chapter();
            chapter.setParagraphs(paragraphs);
            chapter.setTitle(dir.getName());
            chapters.add(chapter);
        }
        return chapters;
    }

    /**
     * 获取章节目录
     * @param driver
     * @return
     */
    public List<Directory> getDirectories(WebDriver driver){

        try {
            List<WebElement> alist = driver.findElements(By.cssSelector("#tbody a"));
            if(alist == null){
                return null;
            }

            List<Directory> directories = ListUtils.newArrayList();
            for(WebElement a : alist){
                String href = a.getAttribute("href");
                String title = a.getAttribute("title");

                Directory dir = new Directory();
                dir.setUrl(href);
                dir.setName(title);
                directories.add(dir);
            }
            return directories;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
