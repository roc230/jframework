package com.roc.jframework.crawler.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * WebDriver的包装器，用于实现常用功能的链式写法
 */
public class WebDriverWrapper {

    private WebDriver driver;
    private WebDriverWait wait;

    public WebDriverWrapper(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    public static WebDriverWrapper wrapper(WebDriver driver){
            WebDriverWrapper wrapper = new WebDriverWrapper(driver);
            return wrapper;
    }

    public WebDriver getWebDriver(){
        return this.driver;
    }

    public WebDriverWrapper get(String url){
        this.driver.get(url);
        return this;
    }

    public WebDriverWrapper untilElementPresence(By by){
        this.wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return this;
    }

    public WebElement findElement(By by){
        try {
            WebElement e = this.driver.findElement(by);
            return e;
        } catch (Exception e1) {
//            e1.printStackTrace();
        }
        return null;
    }

    public List<WebElement> findElements(By by){
        try {
            List<WebElement> list = this.driver.findElements(by);
            return list;
        } catch (Exception e1) {
//            e1.printStackTrace();
        }
        return null;
    }
}
