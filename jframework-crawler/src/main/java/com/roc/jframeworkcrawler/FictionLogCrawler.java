package com.roc.jframeworkcrawler;

import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FictionLogCrawler {

    //入口方法
    void fetchContent(String url){

        WebDriver driver = SeleniumUtils.chrome();
        driver.get(url);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> chapters = getChapter(driver);
        for(int i = 0; i < chapters.size(); i++){
            boolean next = catchContentAndSave(driver, chapters.get(i));
            if(next == false){
                break;
            }
        }

//        catchContentAndSave(driver);

        driver.quit();
    }

    /**
     * 获取章节url集合
     * @param driver
     * @return
     */
    public List getChapter(WebDriver driver){
        WebElement e1 = driver.findElement(By.id("dropdownChapterListButton"));
        e1.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement drag = driver.findElement(By.className("DropdownModal__DropdownModalWrapper-sc-1yp2j29-0"));
        List<WebElement> alist = drag.findElements(By.cssSelector("div div div div a"));
        List<String> chaps = new ArrayList<>();
        for(WebElement e : alist){
//            System.out.println(e.getAttribute("href"));
            //
            chaps.add(e.getAttribute("href"));
            //
        }
        return chaps;
    }

    /**
     * 获取每章节的内容
     * @param driver
     * @param url 章节url
     */
    public boolean catchContentAndSave(WebDriver driver, String url){
        driver.get(url);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //
        WebElement cc = null;
        try {
            cc = driver.findElement(By.className("dFkKsT"));
            if(cc == null){
                return false;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        //
        List<WebElement> list = null;
        try {
            list = cc.findElements(By.tagName("p"));
            if(list == null){
                return false;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        //适配title
        String title = null;
        for(WebElement e : list){
            if(e.getText() != null && e.getText().trim().length() > 0){
                title = e.getText().trim();
                break;
            }
        }

        //适配内容
        StringBuffer content = new StringBuffer();
        for(WebElement e : list){
            if(e.getText() == null){
                break;
            }
            //每段内容处理
            content.append("\b\b"); //首行缩进两格
            content.append(e.getText());
            content.append("\r\n"); //换行
            content.append("\n"); //换行
        }

        //save content to disk
        if(!StringUtils.isNullOrEmpty(content.toString())){
            String dir = "d:/";
            FileUtils.saveAsFile(content.toString(), dir + title + ".txt", true);
        }

        return true;
    }


}
