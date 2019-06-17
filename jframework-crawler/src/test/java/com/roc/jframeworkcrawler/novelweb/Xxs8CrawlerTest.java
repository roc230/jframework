package com.roc.jframeworkcrawler.novelweb;

import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.novelweb.HongxiuCrawler;
import com.roc.jframework.crawler.novelweb.NovelInfo;
import com.roc.jframework.crawler.novelweb.NovelInfoHelper;
import com.roc.jframework.crawler.novelweb.Xxs8Crawler;
import org.junit.Test;

import java.util.List;

public class Xxs8CrawlerTest {

    @Test
    public void test(){
        List<NovelInfo> list = Xxs8Crawler.create()
                .execute("http://www.xxs8.com/shuku/index/main/21/sub/2101/star/0/size/1/flag/0/time/0/type/0/order/0/ini/0.html", 81);
        JsonUtils.printJson(list);
        NovelInfoHelper.saveAsExcel(list, "d:/xxs8.xlsx");
    }
/*
    @Test
    public void tt(){
        String json = FileUtils.readAsString("d:/17k.json");
        List<NovelInfo> list = JsonUtils.fromJson(json, new TypeToken<List<NovelInfo>>(){}.getType());
        NovelInfoHelper.saveAsExcel(list, "d:/17k.xlsx");
    }*/
}
