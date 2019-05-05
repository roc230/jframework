package com.roc.jframework.crawler.novelweb;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.crawler.selenium.DriverPath;
import com.roc.jframework.crawler.selenium.WebDriverBuilder;
import com.roc.jframework.crawler.selenium.WebDriverWrapper;
import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class K17Crawler {

    public static K17Crawler create(){
        return new K17Crawler();
    }

    public List<NovelInfo> execute(String startPage, int pageCount){
        WebDriver driver = null;

        try {
            driver = WebDriverBuilder.create()
                    .driverPath(DriverPath.CHROME_DRIVER_PATH)
                    .userAgent(UserAgent.CHROME)
                    .loadImg(true)
                    .headless(false)
                    .buildChrome();

            WebDriverWrapper wrapper = WebDriverWrapper.wrapper(driver);

            List<NovelInfo> list = getList(wrapper, startPage, pageCount);

            List<NovelInfo> details = getDetail(wrapper, list);

            return details;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(driver != null) driver.quit();
        }

        return null;
    }

    public List<NovelInfo> getDetail(WebDriverWrapper wrapper, List<NovelInfo> infos){
        for(NovelInfo info : infos){
            wrapper.get(info.getUrl()).sleep(500);
            WebElement clickCountE = wrapper.findElement(By.cssSelector("#howmuchreadBook"));
            String clickCount = clickCountE != null ? clickCountE.getText(): "";
            WebElement coverImgE = wrapper.findElement(By.cssSelector(".book"));
            String coverImg = coverImgE != null ? coverImgE.getAttribute("src") : "";
            info.setClickCount(clickCount);
            info.setCoverImg(coverImg);
        }
        return infos;
    }

    public List<NovelInfo> getList(WebDriverWrapper wrapper, String startPage, int pageCount){


        List<NovelInfo> novelInfos = ListUtils.newArrayList();
        String nextPage = startPage;
        for(int i = 0; i < pageCount; i++){
            wrapper.get(nextPage).sleep(500);

            List<WebElement> trlist = wrapper.findElements(By.cssSelector(".alltable > table tbody tr"));
            for(int j = 1; j < trlist.size(); j++){
                WebElement tr = trlist.get(j);
                String category = SeleniumUtils.findElement(tr, By.cssSelector(".td2 a")).getText();
                String title = SeleniumUtils.findElement(tr, By.cssSelector(".td3 a")).getText();
                String href = SeleniumUtils.findElement(tr, By.cssSelector(".td3 a")).getAttribute("href");
                String autor = SeleniumUtils.findElement(tr, By.cssSelector(".td6 a")).getText();
                String status = SeleniumUtils.findElement(tr, By.cssSelector(".td8 em")).getText();
                status = status.equals("连载") ? "连载中" : "已完结";
                String words = SeleniumUtils.findElement(tr, By.cssSelector(".td5")).getText();

                NovelInfo novelInfo = new NovelInfo.Builder()
                        .author(autor)
                        .category(category)
                        .name(title)
                        .status(status)
                        .words(words)
                        .url(href)
                        .build();

                novelInfos.add(novelInfo);
            }

            nextPage = nextPage(nextPage);
        }

        return novelInfos;
    }

    private int getPageNum(String pageUrl){
        String pageNum = StringUtils.findByReg(pageUrl, "(\\d+).html", 1);
        return Integer.valueOf(pageNum);
    }

    private String nextPage(String currentPage){
        int pageNum = getPageNum(currentPage);
        String url = currentPage.replaceAll("\\d+.html", (pageNum+1)+".html");
        return url;
    }


}
