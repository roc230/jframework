package com.roc.jframework.crawler;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.UrlUtils;
import com.roc.jframework.core.component.myhttpclient.HttpRequests;
import com.roc.jframework.core.utils.JsoupUtils;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Directory;
import com.roc.jframework.crawler.entity.Novel;
import org.apache.commons.math3.analysis.function.Abs;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class NovelFreeReadOnlineCrawler extends AbstractCrawler {

    @Override
    public void execute(String url) {
        Novel novel = getNovel(url);
        super.append(false);
        String baseFile = "d:/novel1/en/";
        super.saveAsJson(novel, new File(baseFile + novel.getName() + ".json"));
        super.saveAsTxt(novel, new File(baseFile + novel.getName() + ".txt"));
    }

    public Novel getNovel(String url){
        Novel novel = new Novel();
        String schemaAndDomain = UrlUtils.getSchemaAndDomain(url);
        String html = HttpRequests.create()
                .url(url)
                .method("get")
                .userAgent(UserAgent.CHROME)
                .execute()
                .getContent();
        Document doc = JsoupUtils.parse(html);
        String title = doc.selectFirst(".title h1").text();
        String cover = doc.selectFirst(".imagesCrop > img").attr("href");
        cover = schemaAndDomain + cover;
        novel.setCover(cover);
        Elements ullist = doc.select(".desc > ul");
        if(ullist.size() > 2){
            String author = ullist.get(0).selectFirst("a").text();
            String category = ullist.get(1).selectFirst("li a").text();
            novel.setAuthor(author);
            novel.setCategory(category);
        }

        novel.setName(title);
        novel.setUrl(url);
        novel.setDirectories(getDirectory(doc, schemaAndDomain));
        novel.setChapters(getChapters(novel.getDirectories()));

        return novel;
    }

    private List<Directory> getDirectory(Document doc, String schemaAndDomain){
        Elements lilist  =  doc.select(".chuong > li");
        if(lilist == null || lilist.size() < 1){
            return null;
        }
        List<Directory> dirs =
        lilist.stream()
//                .parallel()
                .map( li -> {
                    Element a = li.selectFirst("a");
                    String name = a.text();
                    String href = a.attr("href");
                    href = schemaAndDomain + href;
                    Directory dir = new Directory();
                    dir.setName(name);
                    dir.setUrl(href);
                    return dir;
                })
                .collect(Collectors.toList());
        return dirs;
    }

    private List<Chapter> getChapters(List<Directory> dirs){
        return dirs.stream()
                .map( d -> {
                    String html = HttpRequests.create()
                            .url(d.getUrl())
                            .userAgent(UserAgent.CHROME)
                            .method("get")
                            .execute()
                            .getContent();
                    Document doc = Jsoup.parse(html);
                    Elements plist = doc.select(".content-center > p");
                    List<String> lines = plist.stream()
                            .map( p -> {
                                if(p.text().equals("Source: www_Novel12_Com")){
                                    return "";
                                }else{
                                    return p.text();
                                }
                            })
                            .collect(Collectors.toList());
                    Chapter chapter = new Chapter();
                    chapter.setTitle(d.getName());
                    chapter.setParagraphs(lines);
                    return chapter;
                })
                .collect(Collectors.toList());
    }
}
