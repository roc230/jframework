package com.roc.jframework.crawler.novelcopyright;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.encodedecode.UnicodeUtils;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.core.component.httpclient.RequestsBuilder;
import com.roc.jframework.core.utils.JsoupUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class ZhangyueSearch {

    public static List<NovelSearch> execute(String bookName){
        List<NovelSearch> list = search(bookName);
        return fetchDetail(list);
    }

    private static List<NovelSearch> fetchDetail(List<NovelSearch> list){
        for(NovelSearch n : list){
            String url = n.getUrl();
            if(StringUtils.isNullOrEmpty(url)){
                continue;
            }
            String html = new RequestsBuilder()
                    .userAgent(UserAgent.CHROME)
                    .charset("utf-8")
                    .url(url)
                    .getAsString();
            if(StringUtils.isNullOrEmpty(html)){
                continue;
            }
            Document doc = JsoupUtils.parse(html);
            //category
            String category = doc.selectFirst(".content .bookInfor .bookL s").text();
            //words
            List<Element> spans = doc.select(".content .bookInfor .bookR .bookinf01 p span");
            String words = "";
            if(spans != null && spans.size() > 2){
                Element span_words = spans.get(1);
                words = span_words.text();
                words = StringUtils.findByReg(words, "字数：(.+)", 1);
            }
            //score
            String score = "";
            Element scoreE = doc.selectFirst(".content .bookInfor .bookR .bookinf01 .bookname span");
            if(scoreE != null){
                score = scoreE.text();
            }

            n.setWords(words);
            n.setCategory(category);
            n.setScore(score);

        }

        return list;
    }

    private static List<NovelSearch> search(String bookeName){
        String searchUrl = "http://www.ireader.com/index.php?ca=search.index&keyword=${keyword}&page=1"
                .replaceAll("\\$\\{keyword\\}", bookeName);
        String html = RequestsBuilder.create()
                .url(searchUrl)
                .userAgent(UserAgent.CHROME)
                .charset("utf-8")
                .getAsString();
        Document doc = JsoupUtils.parse(html);

        List<NovelSearch> list = ListUtils.newArrayList();
        Elements lis = doc.select(".sResult li");
        for(Element e : lis){
            String cover = e.selectFirst(".cover img").attr("src");
            String url = e.selectFirst(".bookMess a").attr("href");
            url = "http://www.ireader.com/" + url;
            String name = e.selectFirst(".bookMess a h3").text();
            String author = e.selectFirst(".author").text();
            author = StringUtils.findByReg(author, "作者：(.+)", 1);
            String brief = e.selectFirst(".brief").text();

            NovelSearch s = new NovelSearch();
            s.setAuthor(author);
            s.setCoverImg(cover);
            s.setName(name);
            s.setUrl(url);

            list.add(s);
        }

        return list;

    }

}
