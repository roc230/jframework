package com.roc.jframework.crawler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.basic.utils.UrlUtils;
import com.roc.jframework.core.component.httpclient.RequestsBuilder;
import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.core.utils.JsoupUtils;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Directory;
import com.roc.jframework.crawler.entity.Novel;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class FictionlogCommonCrawler extends BasicCrawler{

    public static FictionlogCommonCrawler create(){
        return new FictionlogCommonCrawler();
    }

    @Override
    public void execute(String url) {
        Novel novel = this.getDirectories(url);

        List<Chapter> chapters = this.getChapters(novel.getDirectories());

        novel.setChapters(chapters);

        //save as json
        saveAsJson(novel, new File("d:/novel1/" + novel.getName() + ".json"));
        //save as txt
        saveAsTxt(novel, new File("d:/novel1/" + novel.getName() + ".txt"));


        System.out.println("end!");
    }


    private List<Chapter> getChapters(List<Directory> directories){
        if(start < 0 || max > directories.size()){
            System.out.println("参数错误");
        }

        List<Chapter> chapters = ListUtils.newArrayList();
        for(Directory directory : directories){
            String html = RequestsBuilder.create()
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
            }

            Chapter chapter = new Chapter();
            chapter.setTitle(title);
            chapter.setParagraphs(parapraghs);

            chapters.add(chapter);

            return chapters;
        }

        return chapters;
    }

    private Novel getDirectories(String url){
        String html = RequestsBuilder.create()
                .userAgent(UserAgent.CHROME)
                .url(url)
                .getAsString();

        Document doc = JsoupUtils.parse(html);

        String novelName = doc.selectFirst(".BookHeader__BookTitle-sc-7bc82j-5").text();
        Novel novel = new Novel();
        novel.setName(novelName);
        novel.setUrl(url);

        String schemaAndDomain = UrlUtils.getSchemaAndDomain(url);
        List<Directory> directories = ListUtils.newArrayList();
        Elements alist = doc.select(".GroupChapterRow__GroupChapterList-sc-1crfeny-0 .ChapterRow__MainWrapper-r5e2sp-8 > div > div > a");
        for(Iterator<Element> iter = alist.iterator(); iter.hasNext(); ){
            Element a = iter.next();
            String href = schemaAndDomain + a.attr("href");
            String title = a.selectFirst("div").text();

            Directory dir = new Directory();
            dir.setUrl(href);
            dir.setName(title);

            directories.add(dir);
        }
        novel.setDirectories(directories);
        return novel;
    }
}
