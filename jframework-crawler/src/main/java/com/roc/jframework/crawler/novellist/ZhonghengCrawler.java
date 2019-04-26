package com.roc.jframework.crawler.novellist;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.TimerUtils;
import com.roc.jframework.crawler.selenium.DriverPath;
import com.roc.jframework.crawler.selenium.WebDriverBuilder;
import com.roc.jframework.crawler.selenium.WebDriverWrapper;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ZhonghengCrawler {

    public void execute(String startPage, Integer maxPage){
        WebDriver driver = null;
        try {
            driver = new WebDriverBuilder()
                    .driverPath(DriverPath.CHROME_DRIVER_PATH)
                    .userAgent(UserAgent.CHROME)
                    .loadImg(true)
                    .headless(false)
                    .buildChrome();
            WebDriverWrapper wrapper = WebDriverWrapper.wrapper(driver);

            List<NovelData> list = getNovels(wrapper, startPage, maxPage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }

    private List<NovelData> getNovels(WebDriverWrapper driver, String startPage, Integer maxPage){
        driver.get(startPage);
        TimerUtils.sleep(500);
        return null;

    }

}
