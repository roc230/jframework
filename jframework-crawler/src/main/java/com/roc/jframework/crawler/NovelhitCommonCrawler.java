package com.roc.jframework.crawler;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.basic.utils.ListUtils;
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

public class NovelhitCommonCrawler {

    public static NovelhitCommonCrawler create(){
        return new NovelhitCommonCrawler();
    }

    public void execute(String url){
        Novel novel = getDirectories(url);

        List<Chapter> chapters = getChapters(novel.getDirectories(), 30);

        novel.setChapters(chapters);

        String json = JsonUtils.toString(novel);


//        String json = FileUtils.readAsString("d:/novel1/ราชันย์หมาป่ากับceoที่แสนเย็นชา .json");
//        Novel novel = JsonUtils.fromJson(json, Novel.class);

        //save json
        String fileName = novel.getName().substring(0, novel.getName().indexOf(":"));
        File jsonFile = new File("d:/novel1/" + fileName + ".json");
        FileUtils.saveAsFile(json, jsonFile, true);

        //save as txt
        File file = new File("d:/novel1/" + fileName + ".txt");
        saveAsText(novel, file);


        System.out.println("end");

    }

    public void saveAsText(Novel novel, File file){
        StringBuffer sb = new StringBuffer();
        for(Chapter chapter : novel.getChapters()){
            //set title
            sb.append(chapter.getTitle())
                    .append("\r\n")
                    .append("\r\n");
            //set paragraph
            for(String p : chapter.getParagraphs()){
                sb.append("\0\0\0\0\0\0\0\0")
                        .append(p)
                        .append("\r\n")
                        .append("\r\n");
            }
        }
        String c = sb.toString();
        c = removeAdver(c);
        FileUtils.saveAsFile(c, file, true);
    }

    private String removeAdver(String content){
        content = content.replaceAll("\\(.+www\\.google\\.co.+\\)", "");
        return content;
    }

    public List<Chapter> getChapters(List<Directory> directories, Integer max){
        if(max > directories.size()){
            System.out.println("参数错误");
            return null;
        }
        List<Chapter> chapters = ListUtils.newArrayList();
        for(int i = 0; i < max; i++){
            Directory dir = directories.get(i);
            System.out.println("\n===============================\n开始热拔插章节：" + dir.getName() + "\n");
            String html = RequestsBuilder.create()
                    .url(dir.getUrl())
                    .userAgent(UserAgent.CHROME)
                    .getAsString();
            Document doc = JsoupUtils.parse(html);

            List<String> paragraphs = ListUtils.newArrayList();
            Elements spans = doc.select(".m-bottom-40 > p > span");
            if(spans == null){
                continue ;
            }

            if(spans.size() == 1){
                Element span = spans.get(0);
                String[] strings = span.html().split("(\\<br\\>)");
                if(strings == null){
                    continue;
                }
                for(String str : strings){
                    str = str.replaceAll("&nbsp;", " ");
                    paragraphs.add(str.trim());
                }

                Chapter chapter = new Chapter();
                chapter.setTitle(dir.getName());
                chapter.setParagraphs(paragraphs);

                chapters.add(chapter);
            }else{
                for(Iterator<Element> iter = spans.iterator(); iter.hasNext(); ){
                    Element e = iter.next();
                    paragraphs.add(e.text().trim());
                }
                Chapter chapter = new Chapter();
                chapter.setTitle(dir.getName());
                chapter.setParagraphs(paragraphs);
                chapters.add(chapter);
            }


        }
        return chapters;
    }

    public Novel getDirectories(String url){
        String html = RequestsBuilder.create()
                .url(url)
                .userAgent(UserAgent.CHROME)
                .getAsString();
        Document doc = JsoupUtils.parse(html);
        Element helement = doc.selectFirst(".movie-title-name>h1");
        String novelName = helement.text();

        Novel novel = new Novel();
        novel.setName(novelName);
        novel.setUrl(url);

        List<Directory> directories = ListUtils.newArrayList();
        Elements elements = doc.select("article > a");
        for(Iterator<Element> iter = elements.iterator(); iter.hasNext();){
            Element a = iter.next();
            String href = a.attr("href");
            String title = a.selectFirst("h5").text();

            Directory dir = new Directory();
            dir.setName(title);
            dir.setUrl(href);

            directories.add(dir);
        }

        novel.setDirectories(directories);
        return novel;
    }
}
