package com.roc.jframework.crawler;

import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Directory;
import com.roc.jframework.crawler.entity.Novel;
import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class KawebookCrawler {

    public static KawebookCrawler create(){
        return new KawebookCrawler();
    }

    public void execute(){
        Novel novel = new Novel();
        novel.setUrl("https://www.kawebook.com/story/97/%E0%B8%99%E0%B8%B4%E0%B8%A2%E0%B8%B2%E0%B8%A2/%E0%B8%99%E0%B8%B4%E0%B8%A2%E0%B8%B2%E0%B8%A2-%E0%B8%A3%E0%B8%B1%E0%B8%81/%E0%B9%80%E0%B8%88%E0%B9%89%E0%B8%B2%E0%B8%AA%E0%B8%B2%E0%B8%A7%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B9%83%E0%B8%AB%E0%B8%A1%E0%B9%88%E0%B9%81%E0%B8%AB%E0%B9%88%E0%B8%87%E0%B8%AA%E0%B8%81%E0%B8%A5%E0%B8%A5%E0%B8%B9%E0%B9%88");
        novel.setName("เจ้าสาวมือใหม่แห่งสกุลลู่");

        WebDriver driver = SeleniumUtils.chrome();

        List<Directory> directories = getDirectories(driver, novel.getUrl());

        List<Chapter> chapters = getChapters(driver, directories);


    }

    /**
     * 获取章节内容
     * @param driver
     * @param directories
     * @return
     */
    public List<Chapter> getChapters(WebDriver driver, List<Directory> directories){
        for(Directory directory:directories){
            String url = directory.getUrl();
            driver.get(url);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                WebElement div = driver.findElement(By.id("read_content"));
                if(div == null){
                    return null;
                }

                List<WebElement> plist = div.findElements(By.tagName("p"));
                if(ListUtils.isNullOrEmpty(plist)){
                    return null;
                }

                for(WebElement p : plist){
                    String txt = p.getText();
                    List<WebElement> spanlist = p.findElements(By.tagName("span"));
                    if(!ListUtils.isNullOrEmpty(spanlist)){

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 获取章节目录
     * @param driver
     * @return
     */
    public List<Directory> getDirectories(WebDriver driver, String url){
        driver.get(url);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            List<WebElement> list = driver.findElements(By.cssSelector(".feed-rows-post"));
            if(ListUtils.isNullOrEmpty(list)){
               return null;
            }
            WebElement div = list.get(1);
            List<WebElement> alist = div.findElements(By.cssSelector("a"));
            if(ListUtils.isNullOrEmpty(alist)){
                return null;
            }

            List<Directory> directories = ListUtils.newArrayList();
            for(WebElement a : alist){
                String chapterUrl = a.getAttribute("href");

                String chapterName = "";
                WebElement span = a.findElement(By.cssSelector("span[class='purple-color-light']"));
                if(span != null){
                    chapterName = span.getText();
                }

                Directory directory = new Directory();
                directory.setName(chapterName);
                directory.setUrl(chapterUrl);
                directories.add(directory);
            }
            return directories;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
