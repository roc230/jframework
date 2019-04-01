package com.roc.jframework.crawler.selenium;

import com.roc.jframework.basic.utils.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class WebDriverBuilder {
    private String userAgent;
    private String driverPath;
    private Boolean headless = false;
    private Boolean loadImg = true;

    public static WebDriverBuilder create(){
        return new WebDriverBuilder();
    }

    public WebDriver buildChrome(){
        if(StringUtils.isNullOrEmpty(driverPath)){
            throw new RuntimeException("浏览器驱动未设置！");
        }
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        //set useragent
        if(!StringUtils.isNullOrEmpty(userAgent)){
            options.addArguments("--user-agent=" + userAgent);
        }

        //set headless
        if(headless){
            options.addArguments("--headless");
        }

        //set prefs
        Map<String,Object> prefs = new HashMap<>();
        if(!loadImg){
            prefs.put("profile.managed_default_content_settings.images", 2); //不显示图片
        }

        //
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }

    ////////////setter
    public WebDriverBuilder driverPath(String driverPath){
        this.driverPath = driverPath;
        return this;
    }

    public WebDriverBuilder userAgent(String userAgent){
        this.userAgent = userAgent;
        return this;
    }

    public WebDriverBuilder headless(Boolean headless){
        this.headless = headless;
        return this;
    }

    public WebDriverBuilder loadImg(Boolean loadImg){
        this.loadImg = loadImg;
        return this;
    }
}
