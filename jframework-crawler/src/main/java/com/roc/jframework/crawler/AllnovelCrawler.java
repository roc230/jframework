package com.roc.jframework.crawler;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.basic.utils.UrlUtils;
import com.roc.jframework.core.component.myhttpclient.HttpRequests;
import com.roc.jframework.core.utils.JsoupUtils;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Directory;
import com.roc.jframework.crawler.entity.Novel;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.List;

public class AllnovelCrawler extends AbstractCrawler {

    @Override
    public void execute(String url) {
        String html = HttpRequests.create()
                .userAgent(UserAgent.CHROME)
                .url(url)
                .method("get")
                .execute()
                .getContent();
        if(StringUtils.isNullOrEmpty(html)){
            System.out.println("获取小说页面错误");
            return ;
        }
        Document doc = JsoupUtils.parse(html);
//        System.out.println(doc.html());

        String cover = doc.selectFirst(".detail-novel .col-md-7 img").attr("src");
        String title = doc.selectFirst(".detail-novel .col-sm-12 h1").text();

        String schemaAndDomain = UrlUtils.getSchemaAndDomain(url);
        List<Directory> dirs = this.getDirectories(doc, schemaAndDomain);

        List<Chapter> chapters = this.getChapters(dirs);

        Novel novel = new Novel.Builder()
                .cover(cover)
                .chapters(chapters)
                .directories(dirs)
                .url(url)
                .name(title)
                .build();

        super.saveAsJson(novel, new File("d:/novel1/allnovel/" + novel.getName() + ".json"));
        super.saveAsTxt(novel, new File("d:/novel1/allnovel/" + novel.getName() + ".txt"));

    }

    private List<Chapter> getChapters(List<Directory> dirs){

        List<Chapter> chapters = ListUtils.newArrayList();
        for(Directory dir : dirs) {
            Document doc = HttpRequests.create()
                    .url(dir.getUrl())
                    .method("get")
                    .userAgent(UserAgent.CHROME)
                    .execute()
                    .asJsoupDocument();
            Elements para = doc.select(".des_novel > p");
            List<String> plist = ListUtils.newArrayList();
            for(Element p : para){
                plist.add(p.text());
            }
            String title = doc.selectFirst(".title a").text();

            Chapter chapter = new Chapter();
            chapter.setParagraphs(plist);
            chapter.setTitle(title);

            chapters.add(chapter);
        }

        return chapters;

    }


    private List<Directory> getDirectories(Document doc, String schemaAndDomain) {
        List<Directory> dirs = ListUtils.newArrayList();
        Elements alist = doc.select(".list-page-novel table tbody tr a");
        for (Element a : alist) {
            String page = a.text();
            String href = a.attr("href");
//            System.out.println(href);

            Directory dir = new Directory();
            dir.setName(page);
            dir.setUrl(schemaAndDomain + href);
            dirs.add(dir);
        }
        return dirs;
    }
}
