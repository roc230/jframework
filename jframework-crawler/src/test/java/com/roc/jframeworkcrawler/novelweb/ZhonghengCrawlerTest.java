package com.roc.jframeworkcrawler.novelweb;

import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.novelweb.NovelInfo;
import com.roc.jframework.crawler.novelweb.NovelInfoHelper;
import com.roc.jframework.crawler.novelweb.ZhonghengCrawler;
import org.junit.Test;

import java.util.List;

public class ZhonghengCrawlerTest {

    @Test
    public void test(){
        List<NovelInfo> list = ZhonghengCrawler.create()
                .execute("http://book.zongheng.com/store/c31/c3123/b1/u0/p16/v9/s9/t1/u0/i1/ALL.html", 5);
//        .execute("http://book.zongheng.com/store/c31/c3123/b1/u0/p8/v9/s9/t1/u0/i1/ALL.html", 2);
        JsonUtils.printJson(list);
        NovelInfoHelper.saveAsExcel(list, "d:/zhongheng.xlsx");
    }
}
