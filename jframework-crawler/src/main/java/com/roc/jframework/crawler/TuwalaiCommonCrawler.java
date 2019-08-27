package com.roc.jframework.crawler;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.crawler.common.AbstractCrawler;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Directory;
import com.roc.jframework.crawler.entity.Novel;
import com.roc.jframework.crawler.selenium.DriverPath;
import com.roc.jframework.crawler.selenium.WebDriverBuilder;
import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class TuwalaiCommonCrawler extends AbstractCrawler {

    public static TuwalaiCommonCrawler create(){
        return new TuwalaiCommonCrawler();
    }

    /**
     * 执行抓取
     * @param url
     */
    public void execute(String url){
        WebDriver driver = null;
        String novelName = "";
        Novel novel = null;
        List<Directory> directories = null;
        List<Chapter> chapters = null;
        try {
            driver = WebDriverBuilder.create()
                    .driverPath(DriverPath.CHROME_DRIVER_PATH)
                    .headless(true)
                    .loadImg(false)
                    .userAgent(UserAgent.CHROME)
                    .buildChrome();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            driver.get(url);
//        TimerUtils.sleep(2000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#tbody")));

            novelName = null;
            try {
                WebElement p = driver.findElement(By.cssSelector("#story-content p[title]"));
                novelName = p.getAttribute("title");
            } catch (Exception e) {
                e.printStackTrace();
            }

            novel = new Novel();
            novel.setName(novelName);
            novel.setUrl(url);

            directories = getDirectories(driver);

            chapters = getChapters(driver, directories);
        } finally {
            if(driver != null){
                driver.quit();
            }
        }

        novel.setDirectories(directories);
        novel.setChapters(chapters);

        saveAsJson(novel, new File("d:/novel1/" + novel.getName() + ".json"));

        saveAsTxt(novel, new File("d:/novel1/" + novelName + ".txt"));

        System.out.println("end!");

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

            WebDriverWait wait = new WebDriverWait(driver, 5);
            driver.get(dir.getUrl());
//            TimerUtils.sleep(1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#story-detail")));

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
            System.out.println("--->已完成：" + dir.getName());
        }
        return chapters;
    }

    /**
     * 获取章节目录
     * @param driver
     * @return
     */
    public List<Directory> getDirectories(WebDriver driver){
        System.out.println("开始获取目录...");
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
            System.out.println("获取目录完成!");
            return directories;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
