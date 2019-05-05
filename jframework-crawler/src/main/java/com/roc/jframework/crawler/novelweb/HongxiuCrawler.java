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

public class HongxiuCrawler {

    public static HongxiuCrawler create(){
        return new HongxiuCrawler();
    }

    public List<NovelInfo> execute(String startPage, int pageCount){

        WebDriver driver = null;

        try {
            driver = WebDriverBuilder.create()
                    .headless(false)
                    .loadImg(true)
                    .userAgent(UserAgent.CHROME)
                    .driverPath(DriverPath.CHROME_DRIVER_PATH)
                    .buildChrome();
            WebDriverWrapper wrapper = WebDriverWrapper.wrapper(driver);

            List<NovelInfo> list = getList(wrapper, startPage, pageCount);
            return getDetails(wrapper, list);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(driver != null) driver.quit();
        }

        return null;

    }

    private List<NovelInfo> getDetails(WebDriverWrapper wrapper, List<NovelInfo> list){
        for(NovelInfo novelInfo : list){
            String url = novelInfo.getUrl();
            wrapper.get(url).sleep(500);
            List<WebElement> spans = wrapper.findElements(By.cssSelector(".total span"));
            if(spans.size() == 3){
                String clickCount = spans.get(2).getText();
                clickCount = String.valueOf(Double.valueOf(clickCount).intValue() * 10000);
                novelInfo.setClickCount(clickCount);
            }
        }
        return list;
    }

    private List<NovelInfo> getList(WebDriverWrapper wrapper, String startPage, int pageCount){

        List<NovelInfo> novelInfos = ListUtils.newArrayList();
        String nextPage = startPage;
        for(int i = 0; i < pageCount; i++){
            wrapper.get(nextPage).sleep(500);

            List<WebElement> lilist = wrapper.findElements(By.cssSelector(".right-book-list ul li"));
            for(WebElement li : lilist){
                String title = SeleniumUtils.findElement(li, By.cssSelector(".book-info h3 a")).getText();
                String href = SeleniumUtils.findElement(li, By.cssSelector(".book-info h3 a")).getAttribute("href");
                String author = SeleniumUtils.findElement(li, By.cssSelector(".book-info h4 a")).getText();
                String brief = SeleniumUtils.findElement(li, By.cssSelector(".book-info .intro")).getText();

                String category = "";
                String status = "";
                String words = "";
                List<WebElement> spanlist = SeleniumUtils.findElements(li, By.cssSelector(".book-info .tag span"));
                if(spanlist.size() == 3){
                    category = spanlist.get(0).getText();
                    status = spanlist.get(1).getText();
                    words = spanlist.get(2).getText();
                    words = words.replace("万", "");
                    words = String.valueOf(Double.valueOf(words) * 10000);
                }

                NovelInfo novelInfo = new NovelInfo.Builder()
                        .url(href)
                        .words(words)
                        .status(status)
                        .name(title)
                        .category(category)
                        .author(author)
                        .brief(brief)
                        .build();
                novelInfos.add(novelInfo);
            }

            nextPage = nextPage(wrapper.getWebDriver().getCurrentUrl());
        }
        return novelInfos;
    }

    private String nextPage(String currentPage){
        String txt = StringUtils.findByReg(currentPage, "(pageNum=\\d+)", 1);
        String num = StringUtils.findByReg(txt, "=(\\d+)", 1);
        String next = currentPage.replaceAll("pageNum=\\d+", "pageNum="+ (Integer.valueOf(num) + 1));
        return next;
    }
}
