package com.roc.jframework.crawler.novelweb;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.crawler.selenium.DriverPath;
import com.roc.jframework.crawler.selenium.WebDriverBuilder;
import com.roc.jframework.crawler.selenium.WebDriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * 新小说吧
 */
public class Xxs8Crawler {

    public static Xxs8Crawler create(){
        return new Xxs8Crawler();
    }

    public List<NovelInfo> execute(String startPage, int pageCount){
        WebDriver driver = null;

        try {
            driver = WebDriverBuilder.create()
                    .userAgent(UserAgent.CHROME)
                    .driverPath(DriverPath.CHROME_DRIVER_PATH)
                    .loadImg(true)
                    .headless(false)
                    .buildChrome();
            WebDriverWrapper wrapper = WebDriverWrapper.wrapper(driver);
            List<NovelInfo> list = getList(wrapper, startPage, pageCount);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if(driver != null){
                driver.quit();
            }
        }
        return null;
    }

    private List<NovelInfo> getList(WebDriverWrapper wrapper, String startPage, int pageCount){

        List<NovelInfo> novelInfos = ListUtils.newArrayList();
        String nextPage = startPage;
        for(int i = 0; i < pageCount; i++){
            wrapper.get(nextPage).sleep(500);
            List<WebElement> trlist = wrapper.findElements(By.cssSelector(".booklist tbody tr"));
            for(WebElement tr : trlist){
                List<WebElement> tdlist = tr.findElements(By.cssSelector("td"));
                if(tdlist.size() > 5){
                    String category = null;
                    String title = null;
                    String href = null;
                    List<WebElement> alist = tdlist.get(1).findElements(By.cssSelector("a"));
                    if(alist.size() > 1){
                        category = alist.get(0).getText();
                        title = alist.get(1).getText();
                        href = alist.get(1).getAttribute("href");
                    }
                    String author = tdlist.get(2).findElement(By.cssSelector("a")).getText();
                    String words = tdlist.get(3).getText();
                    String clickCount = tdlist.get(4).getText();

                    NovelInfo novelInfo = new NovelInfo.Builder()
                            .url(href)
                            .words(words)
                            .author(author)
                            .category(category)
                            .name(title)
                            .clickCount(clickCount)
                            .build();
                    novelInfos.add(novelInfo);
                }
            }
            nextPage = nextPage(nextPage);
        }
        return novelInfos;
    }

    private String nextPage(String currentPage){
        //http://www.xxs8.com/shuku/index/main/21/sub/2101/star/0/size/1/flag/0/time/0/type/0/order/0/ini/0.html
        if(currentPage.endsWith("0.html")){
            return currentPage.replaceAll(".html", "") + "/p/2.html";
        }else{
            String num = StringUtils.findByReg(currentPage, "/(\\d+).html", 1);
            return currentPage.replaceAll("/\\d+.html", "/" + (Integer.valueOf(num) + 1) + ".html");
        }
    }
}
