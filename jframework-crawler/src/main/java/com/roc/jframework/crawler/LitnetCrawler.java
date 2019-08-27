package com.roc.jframework.crawler;

import com.roc.jframework.basic.utils.TimerUtils;
import com.roc.jframework.core.utils.JsoupUtils;
import com.roc.jframework.crawler.common.AbstractCrawler;
import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LitnetCrawler extends AbstractCrawler {

    @Override
    public void execute(String url) {

        WebDriver driver = null;

        try{
            driver = SeleniumUtils.chrome();
            driver.get(url);
            TimerUtils.sleep(1000);

            Document doc = JsoupUtils.parse(driver.getPageSource());
            String cover = doc.selectFirst(".book-view-cover img").attr("src");
            String title = doc.selectFirst(".roboto").text();

            driver.findElement(By.cssSelector(".book-buttons .btn-primary")).click();
            TimerUtils.sleep(500);
            Select s = SeleniumUtils.findSelectElement(driver, By.cssSelector(".js-chapter-change"));
            int max = s.getOptions().size();
            for(int i = 1; i < max; i++){
                Select s1 = SeleniumUtils.findSelectElement(driver, By.cssSelector(".js-chapter-change"));
                s1.selectByIndex(i);
                TimerUtils.sleep(5000);
            }



        }catch(Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }

    }
}
