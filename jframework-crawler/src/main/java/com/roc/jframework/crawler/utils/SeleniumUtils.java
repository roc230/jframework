package com.roc.jframework.crawler.utils;

import com.roc.jframework.basic.constants.UserAgent;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

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

    /**
     * 同步执行JS
     * @param driver
     * @param js
     * @param params
     * @return
     */
    public static Object executeJS(WebDriver driver, String js, Object params){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        return executor.executeScript(js, params);
    }

    /**
     * 异步执行JS
     * @param driver
     * @param js
     * @param params
     * @return
     */
    public static Object executeAsyncJS(WebDriver driver, String js, Object params){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        return executor.executeAsyncScript(js, params);
    }

    /**
     * 点击操作
     * @param driver
     * @param by
     */
    public static void click(WebDriver driver, By by){
        WebElement e = driver.findElement(by);
        if(e == null){
            throw new RuntimeException("没找到要点击的元素");
        }
        e.click();
    }

    /**
     * 消除内容操作
     * @param driver
     * @param by
     */
    public static void clear(WebDriver driver, By by){
        WebElement e = driver.findElement(by);
        if(e == null){
            throw new RuntimeException("没找到要消除内容的元素");
        }
        e.clear();
    }

    /**
     * 查找Select页面元素
     * @param driver
     * @param by
     * @return
     */
    public static Select findSelectElement(WebDriver driver, By by){
        WebElement e = driver.findElement(by);
        if(e == null){
            throw new RuntimeException("没找到Select元素");
        }
        Select s = new Select(e);
        return s;
    }

    public static Alert getAlert(WebDriver driver){
        return driver.switchTo().alert();
    }
}
