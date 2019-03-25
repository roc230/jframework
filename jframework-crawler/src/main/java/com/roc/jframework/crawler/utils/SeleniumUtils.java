package com.roc.jframework.crawler.utils;

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
}
