package com.roc.jframework.crawler.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SeleniumUtils {

    /**
     * 打开一个Chrome Webdriver
     * @return
     */
    public static WebDriver chrome(){
        System.setProperty("webdriver.chrome.driver","d:/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver chromeSilence(){
        System.setProperty("webdriver.chrome.driver","d:/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    /**
     * 检查HTML节点是否为null
     * @param webElement
     * @return
     */
    public static boolean checkWebElement(WebElement webElement){
        if(webElement == null){
            System.err.println("HTML节点为null");
            return false;
        }
        return true;
    }

    /**
     * 打印多个WebElement的text内容
     * @param elist
     */
    public static void printText(List<WebElement> elist){
        for(WebElement e : elist){
            System.out.println(e.getText());
        }
    }

    public static WebElement findElement(WebDriver e, By by){
        try{
            return e.findElement(by);
        }catch (Exception e1){

        }
        return null;
    }

    public static WebElement findElement(WebElement e, By by){
        try{
            return e.findElement(by);
        }catch (Exception e1){

        }
        return null;
    }

    public static List<WebElement> findElements(WebDriver e, By by){
        try {
            return e.findElements(by);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public static List<WebElement> findElements(WebElement e, By by){
        try {
            return e.findElements(by);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
