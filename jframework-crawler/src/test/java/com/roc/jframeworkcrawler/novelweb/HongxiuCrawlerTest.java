package com.roc.jframeworkcrawler.novelweb;

import com.google.gson.reflect.TypeToken;
import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.novelweb.HongxiuCrawler;
import com.roc.jframework.crawler.novelweb.K17Crawler;
import com.roc.jframework.crawler.novelweb.NovelInfo;
import com.roc.jframework.crawler.novelweb.NovelInfoHelper;
import org.junit.Test;

import java.util.List;

public class HongxiuCrawlerTest {

    @Test
    public void test(){
        List<NovelInfo> list = HongxiuCrawler.create()
                .execute("https://www.hongxiu.com/all?pageNum=1&pageSize=10&gender=2&catId=30020&isFinish=-1&isVip=-1&size=6&updT=-1&orderBy=0", 2);
        JsonUtils.printJson(list);
        NovelInfoHelper.saveAsExcel(list, "d:/hongxiu.xlsx");
    }
/*
    @Test
    public void tt(){
        String json = FileUtils.readAsString("d:/17k.json");
        List<NovelInfo> list = JsonUtils.fromJson(json, new TypeToken<List<NovelInfo>>(){}.getType());
        NovelInfoHelper.saveAsExcel(list, "d:/17k.xlsx");
    }*/
}
