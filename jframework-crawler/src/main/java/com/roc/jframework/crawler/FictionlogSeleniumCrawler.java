package com.roc.jframework.crawler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.basic.utils.UrlUtils;
import com.roc.jframework.core.component.httpclient.RequestsBuilder;
import com.roc.jframework.core.utils.JsoupUtils;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Directory;
import com.roc.jframework.crawler.entity.Novel;
import com.roc.jframework.crawler.selenium.DriverPath;
import com.roc.jframework.crawler.selenium.WebDriverBuilder;
import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class FictionlogSeleniumCrawler extends AbstractCrawler {

    public static FictionlogSeleniumCrawler create(){
        return new FictionlogSeleniumCrawler();
    }

    @Override
    public void execute(String url) {
        WebDriver driver = WebDriverBuilder.create()
                .driverPath(DriverPath.CHROME_DRIVER_PATH)
                .headless(false)
                .loadImg(true)
                .userAgent(UserAgent.CHROME)
                .buildChrome();

        Novel novel = this.getDirectories(driver, url);

        List<Chapter> chapters = this.getChapters(driver, novel.getDirectories());

        novel.setChapters(chapters);

        //save as json
        saveAsJson(novel, new File("d:/novel1/" + novel.getName() + ".json"));
        //save as txt
        saveAsTxt(novel, new File("d:/novel1/" + novel.getName() + ".txt"));


        driver.quit();
        System.out.println("end!");
    }


    private List<Chapter> getChapters(WebDriver driver, List<Directory> directories){
        if(start < 0 || max > directories.size()){
            System.out.println("参数错误");
        }

        List<Chapter> chapters = ListUtils.newArrayList();
        for(int i = 0; i < max; i++){
            Directory directory = directories.get(i);
            System.out.println("\n开始抓取章节: " + directory.getName() + "\n");
            WebDriverWait wait = new WebDriverWait(driver, 5);
            driver.get(directory.getUrl());
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#__NEXT_DATA__")));

            Document doc = JsoupUtils.parse(driver.getPageSource());

            Element js = doc.selectFirst("#__NEXT_DATA__");
            String json = js.html();
            JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
            JsonObject je = obj.get("props").getAsJsonObject()
                    .get("serverState").getAsJsonObject();

            JsonObject first = null;
            if(je.entrySet().size() > 0){
                first = je.entrySet().iterator().next().getValue().getAsJsonObject();
            }

            String title = first.get("title").getAsString();
            List<String> parapraghs = ListUtils.newArrayList();
            JsonArray blocks = first.getAsJsonObject("contentRawState")
                    .getAsJsonObject("json")
                    .getAsJsonArray("blocks");
            for(JsonElement e : blocks){
                JsonObject jo = e.getAsJsonObject();
                String p = jo.get("text").getAsString();

                if(p.startsWith("----------")){
                    break;
                }
                if(!StringUtils.isNullOrEmpty(p)){
                    parapraghs.add(p);
                }
            }
            /*String html = RequestsBuilder.create()
                    .userAgent(UserAgent.CHROME)
                    .url(directory.getUrl())
                    .getAsString();
            Document doc = JsoupUtils.parse(html);

            Element js = doc.selectFirst("#__NEXT_DATA__");
            String json = js.html();
            JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
            JsonObject je = obj.get("props").getAsJsonObject()
                    .get("serverState").getAsJsonObject();

            JsonObject first = null;
            if(je.entrySet().size() > 0){
                first = je.entrySet().iterator().next().getValue().getAsJsonObject();
            }

            String title = first.get("title").getAsString();
            List<String> parapraghs = ListUtils.newArrayList();
            JsonArray blocks = first.getAsJsonObject("contentRawState")
                    .getAsJsonObject("json")
                    .getAsJsonArray("blocks");
            for(JsonElement e : blocks){
                JsonObject jo = e.getAsJsonObject();
                String p = jo.get("text").getAsString();

                if(p.startsWith("----------")){
                    break;
                }
                if(!StringUtils.isNullOrEmpty(p)){
                    parapraghs.add(p);
                }
            }*/

            Chapter chapter = new Chapter();
            chapter.setTitle(title);
            chapter.setParagraphs(parapraghs);

            chapters.add(chapter);

        }

        return chapters;
    }

    private Novel getDirectories(WebDriver driver, String url){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".TableOfContent__ChapterListSection-cl18wo-0")));

        WebElement e = SeleniumUtils.findElement(driver, By.cssSelector(".BookHeader__BookTitle-sc-7bc82j-5"));
        if(e == null){
            return null;
        }
        String novelName = e.getText();
        Novel novel = new Novel();
        novel.setName(novelName);
        novel.setUrl(url);

        List<WebElement> alist = SeleniumUtils.findElements(driver, By.cssSelector(".GroupChapterRow__GroupChapterList-sc-1crfeny-0 .ChapterRow__MainWrapper-r5e2sp-8 > div > div > a"));
        if(alist == null){
            return null;
        }

        List<Directory> directories = ListUtils.newArrayList();
        for(int i = 0; i < alist.size(); i++){
            if(i%2 == 0){
                WebElement we = alist.get(i);
                String href = we.getAttribute("href");

                WebElement titleE = SeleniumUtils.findElement(we, By.cssSelector("div"));
                if(titleE == null){
                    return null;
                }
                String title = titleE.getText();
                Directory dir = new Directory();
                dir.setUrl(href);
                dir.setName(title);

                directories.add(dir);
            }
            novel.setDirectories(directories);

        }
        return novel;


        /*String html = RequestsBuilder.create()
                .userAgent(UserAgent.CHROME)
                .url(url)
                .contentType("text/plain; charset=utf-8")
                .header("access-control-allow-origin", "https://fictionlog.co")
                .getAsString();

        Document doc = JsoupUtils.parse(html);

        String novelName = doc.selectFirst(".BookHeader__BookTitle-sc-7bc82j-5").text();
        Novel novel = new Novel();
        novel.setName(novelName);
        novel.setUrl(url);

        String schemaAndDomain = UrlUtils.getSchemaAndDomain(url);
        List<Directory> directories = ListUtils.newArrayList();
        Elements alist = doc.select(".GroupChapterRow__GroupChapterList-sc-1crfeny-0 .ChapterRow__MainWrapper-r5e2sp-8 > div > div > a");
        for(int i = 0; i < alist.size(); i++){
            if(i%2 == 0){
                Element a = alist.get(i);
                String href = schemaAndDomain + a.attr("href");
                String title = a.selectFirst("div").text();

                Directory dir = new Directory();
                dir.setUrl(href);
                dir.setName(title);

                directories.add(dir);
            }
            i++;
        }
        novel.setDirectories(directories);
        return novel;*/
    }
}
