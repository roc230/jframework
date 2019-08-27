package com.roc.jframework.crawler;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.basic.utils.TimerUtils;
import com.roc.jframework.basic.utils.UrlUtils;
import com.roc.jframework.core.component.myhttpclient.HttpRequests;
import com.roc.jframework.core.utils.JsoupUtils;
import com.roc.jframework.crawler.common.AbstractCrawler;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Directory;
import com.roc.jframework.crawler.entity.Novel;
import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PublicBookshelfCrawler extends AbstractCrawler {

    @Override
    public void execute(String url) {

        WebDriver driver = null;
        try{
            driver = SeleniumUtils.chrome();

            driver.get(url);
            TimerUtils.sleep(2000);

            Document doc = JsoupUtils.parse(driver.getPageSource());
            Novel novel = new Novel();

            String title = doc.selectFirst(".bookDetails > h1").text();
            String cover = doc.selectFirst(".bookThumbGiant img").attr("src");
            String schemaAndDomain = UrlUtils.getSchemaAndDomain(url);
            cover = schemaAndDomain + cover;
            String author = doc.selectFirst(".book-author-name-border a").text();

            List<Directory> dirs = this.getDirectories(doc);
            List<Chapter> chapters = this.getChepters(dirs);

            novel.setName(title);
            novel.setCover(cover);
            novel.setUrl(url);
            novel.setAuthor(author);
            novel.setDirectories(dirs);
            novel.setChapters(chapters);


            super.saveAsJson(novel, new File("d:/novel1/public-bookshelf/" + novel.getName() + ".json"));
            super.saveAsTxt(novel, new File("d:/novel1/public-bookshelf/" + novel.getName() + ".txt"));


        }catch(Exception e){

        }finally {
            driver.quit();
        }

/*
        Document doc = HttpRequests.create()
                .url(url)
                .method("get")
                .userAgent(UserAgent.CHROME)
                .execute()
                .asJsoupDocument();
        if(doc == null){
            return ;
        }


        Novel novel = new Novel();

        String title = doc.selectFirst(".bookDetails > h1").text();
        String cover = doc.selectFirst(".bookThumbGiant img").attr("src");
        String schemaAndDomain = UrlUtils.getSchemaAndDomain(url);
        cover = schemaAndDomain + cover;
        String author = doc.selectFirst(".book-author-name-border a").text();

        List<Directory> dirs = this.getDirectories(doc);
        List<Chapter> chapters = this.getChepters(dirs);

        novel.setName(title);
        novel.setCover(cover);
        novel.setUrl(url);
        novel.setAuthor(author);
        novel.setDirectories(dirs);
        novel.setChapters(chapters);


        super.saveAsJson(novel, new File("d:/novel1/public-bookshelf/" + novel.getName() + ".json"));
        super.saveAsTxt(novel, new File("d:/novel1/public-bookshelf/" + novel.getName() + ".txt"));*/

    }

    public List<Directory> getDirectories(Document doc){
        List<Directory> dirs = new ArrayList<>();
        Elements aList = doc.select(".bookLeftNav ul > li > a");
        for(Element a : aList){
            Directory dir = new Directory();
            dir.setUrl(a.attr("href"));
            dir.setName(a.text().trim());
            dirs.add(dir);
        }
        return dirs;
    }

    public List<Chapter> getChepters(List<Directory> dirs){

        List<Chapter> chapters = new ArrayList<>();
        for(Directory dir : dirs) {

            Document doc = HttpRequests.create()
                    .userAgent(UserAgent.CHROME)
                    .method("get")
                    .url(dir.getUrl())
                    .execute()
                    .asJsoupDocument();

            String slider = doc.selectFirst(".bookSlider > h1").text();
            String str = StringUtils.findByReg(slider, "\\((.*)\\)", 1);
            String chapterName = StringUtils.findByReg(str, "([^,]+),", 1);
            String index = StringUtils.findByReg(str, "page (\\d+) of", 1);
            String total = StringUtils.findByReg(str, " of (\\d+)", 1);

            List<String> plist = new ArrayList<>();
            Elements spans = doc.select("#text > p > span");
            for (Element span : spans) {
                plist.add(span.text());
            }

            if(!StringUtils.isAnyNullOrEmpty(index, total)){
                int start = Integer.valueOf(index);
                int end = Integer.valueOf(total);
                Document next = doc;
                for(int i = start; i < end; i++){
                    String nextUrl = next.selectFirst("#nextPage > a").attr("href");
                    next = HttpRequests.create()
                            .method("get")
                            .url(nextUrl)
                            .userAgent(UserAgent.CHROME)
                            .execute()
                            .asJsoupDocument();
                    Elements _spans = next.select("#text > p > span");
                    for (Element span : _spans) {
                        plist.add(span.text());
                    }
                }
            }

            Chapter chapter = new Chapter();
            chapter.setTitle(chapterName);
            chapter.setParagraphs(plist);

            chapters.add(chapter);

        }
        return chapters;
    }

}
