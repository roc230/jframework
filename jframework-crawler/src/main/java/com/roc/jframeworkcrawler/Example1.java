package com.roc.jframeworkcrawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Example1 {

    public static void main(String[] args) throws Exception{
        Example1 e = new Example1();
        String url = "https://fictionlog.co/c/5c80da905c8ef5fde886e838";
        String r = e.get(url);
        System.out.println(r);
    }

    public String get(String url) throws Exception{
        System.setProperty("webdriver.chrome.driver","d:/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        Thread.sleep(2000);
        String c = driver.findElement(By.className("dFkKsT")).getText();
        driver.quit();
        return c;
    }
}
