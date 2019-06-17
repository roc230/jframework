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

public class TaduwenxueCrawler {

    public static TaduwenxueCrawler create(){
        return new TaduwenxueCrawler();
    }

    public List<NovelInfo> execute(String startPage, int pageCount){
        WebDriver driver = null;

        try {
            driver = WebDriverBuilder.create()
                    .headless(false)
                    .loadImg(true)
                    .driverPath(DriverPath.CHROME_DRIVER_PATH)
                    .userAgent(UserAgent.CHROME)
                    .buildChrome();
            WebDriverWrapper wrapper = WebDriverWrapper.wrapper(driver);
            List<NovelInfo> list = getList(wrapper, startPage, pageCount);
            return getDetails(wrapper, list);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(driver != null){
                driver.quit();
            }
        }


        return null;
    }

    private List<NovelInfo> getDetails(WebDriverWrapper wrapper, List<NovelInfo> list){
        for(NovelInfo ni : list){
            wrapper.get(ni.getUrl()).sleep(500);
            List<WebElement> spans = wrapper.findElements(By.cssSelector(".datum span"));
            if(spans.size() > 1){
                String clickCount = SeleniumUtils.findElement(spans.get(1), By.cssSelector("em")).getText();
                if(clickCount.contains("万")){
                    clickCount = clickCount.replaceAll("万", "");
                    Double tmp = Double.valueOf(clickCount) * 10000;
                    clickCount = String.valueOf(tmp.intValue());
                    ni.setClickCount(clickCount);
                }else{
                    ni.setClickCount(clickCount);
                }
            }
            String coverImg = wrapper.findElement(By.cssSelector(".bookImg img")).getAttribute("src");
            ni.setCoverImg(coverImg);
        }
        return list;
    }

    private List<NovelInfo> getList(WebDriverWrapper wrapper, String startPage, int pageCount){

        List<NovelInfo> novelInfos = ListUtils.newArrayList();
        String nextPage = startPage;
        for(int i = 0; i < pageCount; i++){
            wrapper.get(nextPage).sleep(500);

            List<WebElement> list = wrapper.findElements(By.cssSelector(".results .bookList li .rtList"));
            for(WebElement e : list){
                String title = SeleniumUtils.findElement(e, By.cssSelector(".bookNm")).getText();
                String href = SeleniumUtils.findElement(e, By.cssSelector(".bookNm")).getAttribute("href");
                String brief = SeleniumUtils.findElement(e, By.cssSelector(".bookIntro")).getText();
                String author = SeleniumUtils.findElement(e, By.cssSelector(".authorNm")).getText();
                String category = SeleniumUtils.findElement(e, By.cssSelector(".classifyLt")).getText();
                String words = null;
                List<WebElement> spans = SeleniumUtils.findElements(e, By.cssSelector(".condition span"));
                if(spans.size() > 1){
                    words = spans.get(1).getText();
                    if(words.contains("万字")){
                        words = words.replace("万字", "").trim();
                        Double tmp = Double.valueOf(words) * 10000;
                        words = String.valueOf(tmp.intValue());
                    }else if(words.contains("字")){
                        words = words.replace("字", "").trim();
                    }
                }
                String status = SeleniumUtils.findElement(e, By.cssSelector(".condition a")).getText();
                status = status.equals("完结") ? "已完结" : "连载中";

                NovelInfo novelInfo = new NovelInfo.Builder()
                        .name(title)
                        .category(category)
                        .author(author)
                        .brief(brief)
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

    private String nextPage(String currentPage){
//        http://www.tadu.com/store/131-a-1-5-a-20-p-2-122
        String num = StringUtils.findByReg(currentPage, "p-(\\d+)-", 1);
        String next = currentPage.replaceAll("p-\\d+-", "p-" + (Integer.valueOf(num) + 1) + "-");
        return next;
    }
}
