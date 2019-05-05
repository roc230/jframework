package com.roc.jframeworkcrawler.novelweb;

import com.google.gson.reflect.TypeToken;
import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.novelweb.K17Crawler;
import com.roc.jframework.crawler.novelweb.NovelInfo;
import com.roc.jframework.crawler.novelweb.NovelInfoHelper;
import com.roc.jframework.crawler.novelweb.ZhonghengCrawler;
import org.junit.Test;

import java.util.List;

public class K17CrawlerTest {

    @Test
    public void test(){
        List<NovelInfo> list = K17Crawler.create()
                .execute("http://all.17k.com/lib/book/3_17_134_1_____151.html", 50);
        JsonUtils.printJson(list);
        NovelInfoHelper.saveAsExcel(list, "d:/17k.xlsx");
    }

    @Test
    public void tt(){
        String json = FileUtils.readAsString("d:/17k.json");
        List<NovelInfo> list = JsonUtils.fromJson(json, new TypeToken<List<NovelInfo>>(){}.getType());
        NovelInfoHelper.saveAsExcel(list, "d:/17k.xlsx");
    }
}
