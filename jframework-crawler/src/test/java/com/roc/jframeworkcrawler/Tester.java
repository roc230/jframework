package com.roc.jframeworkcrawler;

import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tester {

    public static void main(String[] args){
        try {
            a();
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public static void a() throws Exception{
        WebDriver driver = SeleniumUtils.chrome();
        driver.get("https://www.baidu.com/");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement e = driver.findElement(By.id("aaa"));

        driver.quit();
    }
}
