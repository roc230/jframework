package com.roc.jframework.crawler.novelweb;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.basic.utils.TimerUtils;
import com.roc.jframework.crawler.entity.Novel;
import com.roc.jframework.crawler.selenium.DriverPath;
import com.roc.jframework.crawler.selenium.WebDriverBuilder;
import com.roc.jframework.crawler.selenium.WebDriverWrapper;
import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * 平台文学爬虫
 */
public class PingzhiwenxueCrawler {

    public static PingzhiwenxueCrawler create(){
        return new PingzhiwenxueCrawler();
    }

    public List<NovelInfo> execute(String startPage, int maxPage){
        WebDriver driver = null;
        try{
            driver = WebDriverBuilder.create()
                    .driverPath(DriverPath.CHROME_DRIVER_PATH)
                    .userAgent(UserAgent.CHROME)
                    .loadImg(true)
                    .headless(false)
                    .buildChrome();
            WebDriverWrapper wrapper = WebDriverWrapper.wrapper(driver);
            return fetchClickCount(wrapper, getList(wrapper, startPage, maxPage));
        }catch(Exception e){}
        finally {
            if(driver != null) driver.quit();
        }
        return null;
    }

    private List<NovelInfo> getList(WebDriverWrapper wrapper, String startPage, int maxPage){

            List<NovelInfo> novels = ListUtils.newArrayList();
            String nextPage = startPage;
            for(int i = 0; i < maxPage; i++){
                if(i == 0){
                    wrapper.get(nextPage);
                }
                wrapper.untilElementPresence(By.cssSelector(".storeRight"));
                List<WebElement> items = wrapper.findElements(By.cssSelector(".storeRight .tabItem .commonDescript li"));
                if(ListUtils.isNullOrEmpty(items)){
                    System.out.println("没有找到小说目录");
                    break;
                }
                for(WebElement item : items){
                    String title = SeleniumUtils.findElement(item, By.cssSelector(".title")).getText();
                    System.out.println(title);
                    String href = SeleniumUtils.findElement(item, By.cssSelector(".title")).getAttribute("href");
                    String brief = SeleniumUtils.findElement(item, By.cssSelector(".des")).getText();
                    List<WebElement> elist = SeleniumUtils.findElements(item, By.cssSelector(".message a"));
                    if(elist == null || elist.size() != 4){
                        System.out.println("获取小说人气数据失败!");
                        continue;
                    }
                    String author = elist.get(0).getText();
                    String category = elist.get(1).getText();
                    String status = elist.get(2).getText();
                    String words = elist.get(3).getText();

                    String coverImg = "";
                    WebElement cover = wrapper.findElement(By.cssSelector(".Scover img"));
                    if(cover != null){
                        coverImg = cover.getAttribute("src");
                    }

                    NovelInfo ni = new NovelInfo.Builder()
                            .author(author)
                            .category(category)
                            .status(status)
                            .words(words)
                            .name(title)
                            .url(href)
                            .brief(brief)
                            .coverImg(coverImg)
                            .build();

                    novels.add(ni);
                }
                //next
                if(i+1 == maxPage){
                    break;
                }
                WebElement nextBtn = wrapper.findElement(By.linkText("下一页"));
                if(nextBtn == null){
                    break;
                }
                nextBtn.click();
            }
            return novels;
    }

    private List<NovelInfo> fetchClickCount(WebDriverWrapper wrapper, List<NovelInfo> novelInfos){
        novelInfos.stream()
                .forEach( s -> {
                    String url = s.getUrl();
                    wrapper.get(url);
                    TimerUtils.sleep(500);
                    List<WebElement> elements = wrapper.findElements(By.cssSelector(".d-author > a"));
                    if(elements != null && elements.size() == 5){
                        String clickCount = elements.get(4).getText();
                        clickCount = StringUtils.findByReg(clickCount, "(\\d+)次点击", 1);
                        s.setClickCount(clickCount);
                    }
                });
        return novelInfos;
    }

}
