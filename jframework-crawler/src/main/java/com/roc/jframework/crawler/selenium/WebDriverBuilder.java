package com.roc.jframework.crawler.selenium;

import com.roc.jframework.basic.utils.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * WebDriver创建者模式类
 */
public class WebDriverBuilder {
    private String userAgent;
    private String driverPath;
    private Boolean headless = false;
    private Boolean loadImg = true;
    private Boolean maximizeWindow = false;

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
            options.addArguments("--headless", "--no-sandbox", "--disable-gpu", "test-type");
        }

        //set prefs
        Map<String,Object> prefs = new HashMap<>();
        if(!loadImg){
            prefs.put("profile.managed_default_content_settings.images", 2); //不显示图片
        }

        //
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        //窗口最大化
        if(maximizeWindow){
            driver.manage().window().maximize();
        }
        return driver;
    }

    ////////////setter

    /**
     * 设置浏览器驱动
     * @param driverPath
     * @return
     */
    public WebDriverBuilder driverPath(String driverPath){
        this.driverPath = driverPath;
        return this;
    }

    /**
     * 设置UserAgent
     * @param userAgent
     * @return
     */
    public WebDriverBuilder userAgent(String userAgent){
        this.userAgent = userAgent;
        return this;
    }

    /**
     * 设置是否以无头浏览方式，true:无头，false:有头
     * @param headless
     * @return
     */
    public WebDriverBuilder headless(Boolean headless){
        this.headless = headless;
        return this;
    }

    /**
     * 是否加载图片, true:加载，false:不加载
     * @param loadImg
     * @return
     */
    public WebDriverBuilder loadImg(Boolean loadImg){
        this.loadImg = loadImg;
        return this;
    }

    /**
     * 是否最大化窗口, true:最大化，false：不最大化
     * @param maximizeWindow
     * @return
     */
    public WebDriverBuilder maximiazeWindow(Boolean maximizeWindow){
        this.maximizeWindow = maximizeWindow;
        return this;
    }
}
