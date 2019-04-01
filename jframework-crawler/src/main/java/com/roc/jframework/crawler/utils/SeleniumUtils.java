package com.roc.jframework.crawler.utils;

import com.roc.jframework.basic.constants.UserAgent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeleniumUtils {

    /**
     * 打开一个Chrome Webdriver
     * @return
     */
    public static WebDriver chrome(){
        System.setProperty("webdriver.chrome.driver","d:/chromedriver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        //set useragent
        options.addArguments("--user-agent=" + UserAgent.CHROME);

        //set prefs
        Map<String,Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.images", 2); //不显示图片
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        return driver;
    }

    public static WebDriver chromeSilence(){
        System.setProperty("webdriver.chrome.driver","d:/chromedriver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        //set useragent
        options.addArguments("--user-agent=" + UserAgent.CHROME);

        //set headless
        options.addArguments("--headless", "--disable-gpu");

        //set prefs
        Map<String,Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.images", 2); //不显示图片
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
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
