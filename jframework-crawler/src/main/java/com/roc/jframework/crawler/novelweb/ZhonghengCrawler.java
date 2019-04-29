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

public class ZhonghengCrawler {

    public static ZhonghengCrawler create(){
        return new ZhonghengCrawler();
    }

    public List<NovelInfo> execute(String startPage, int maxPage){
        WebDriver driver = null;
        try{
            driver = WebDriverBuilder.create()
                    .headless(false)
                    .loadImg(true)
                    .userAgent(UserAgent.CHROME)
                    .driverPath(DriverPath.CHROME_DRIVER_PATH)
                    .buildChrome();
            WebDriverWrapper wrapper = WebDriverWrapper.wrapper(driver);

            List<NovelInfo> novelInfos = getList(wrapper, startPage, maxPage);
            return fetchDetail(wrapper, novelInfos);

        }catch(Exception e){

        }finally{
            if(driver != null) driver.quit();
        }
        return null;
    }

    private List<NovelInfo> getList(WebDriverWrapper wrapper, String startPage, int maxPage){
        List<NovelInfo> novelInfos = ListUtils.newArrayList();
        for(int i  = 0; i < maxPage; i++){
            System.out.println("第" + (i+1) + "页：");
            if(i == 0){
                wrapper.get(startPage);
            }
            wrapper.untilElementPresence(By.cssSelector(".store_list_wrap"));
            List<WebElement> bookbox = wrapper.findElements(By.cssSelector(".store_collist .bookbox"));
            if(ListUtils.isNullOrEmpty(bookbox)){
                System.out.println("没找到书籍");
                return null;
            }

            for(WebElement book : bookbox){
                WebElement bookinfo = SeleniumUtils.findElement(book, By.cssSelector(".bookinfo"));
                if(bookinfo == null){
                    System.out.println("没找到书信息");
                    continue;
                }
                String name = SeleniumUtils.findElement(bookinfo, By.cssSelector(".bookname a")).getText();
                String href = SeleniumUtils.findElement(bookinfo, By.cssSelector(".bookname a")).getAttribute("href");
                List<WebElement> alist = SeleniumUtils.findElements(book, By.cssSelector(".bookilnk a"));
                String author = "";
                String category = "";
                if(alist != null && alist.size() > 1){
                    author = alist.get(0).getText();
                    category = alist.get(1).getText();
                }
                String status = SeleniumUtils.findElement(book, By.cssSelector(".bookilnk span")).getText();
                String brief = SeleniumUtils.findElement(book, By.cssSelector(".bookintro")).getText();
                NovelInfo ni = new NovelInfo.Builder()
                        .brief(brief)
                        .url(href)
                        .name(name)
                        .status(status)
                        .category(category)
                        .author(author)
                        .build();
                novelInfos.add(ni);
            }

            if(i + 1 == maxPage){
                break;
            }

            WebElement nextBtn = wrapper.findElement(By.linkText(">"));
            if(nextBtn == null){
                break;
            }
            nextBtn.click();
        }
        return novelInfos;
    }

    private List<NovelInfo> fetchDetail(WebDriverWrapper wrapper, List<NovelInfo> list){
        list.stream()
                .forEach(s -> {
                    wrapper.get(s.getUrl());
                    wrapper.untilElementPresence(By.cssSelector(".booktitle"));
                    String booknumber = wrapper.findElement(By.cssSelector(".booktitle .booknumber")).getText();
//                    System.out.println(booknumber);
                    String clickCount = StringUtils.findByReg(booknumber, "总点击：(\\d+)", 1);
                    String words = StringUtils.findByReg(booknumber, "总字数：(\\d+)", 1);
                    s.setClickCount(clickCount);
                    s.setWords(words);
                });
        return list;
    }
}
