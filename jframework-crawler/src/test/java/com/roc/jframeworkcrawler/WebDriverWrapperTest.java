package com.roc.jframeworkcrawler;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.crawler.selenium.DriverPath;
import com.roc.jframework.crawler.selenium.WebDriverBuilder;
import com.roc.jframework.crawler.selenium.WebDriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverWrapperTest {

    public static void main(String[] args){
        WebDriver driver = WebDriverBuilder.create()
                .maximiazeWindow(false)
                .headless(false)
                .loadImg(true)
                .userAgent(UserAgent.CHROME)
                .driverPath(DriverPath.CHROME_DRIVER_PATH)
                .buildChrome();

        try {
            WebElement e = WebDriverWrapper.wrapper(driver)
                    .get("https://www.baidu.com/")
                    .untilElementPresence(By.cssSelector("#form"))
                    .findElement(By.cssSelector("#form"));
            System.out.println(e.getTagName());
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            driver.quit();
        }


    }
}
