package com.roc.jframeworkcrawler.novelweb;

import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.novelweb.HongxiuCrawler;
import com.roc.jframework.crawler.novelweb.NovelInfo;
import com.roc.jframework.crawler.novelweb.NovelInfoHelper;
import com.roc.jframework.crawler.novelweb.TaduwenxueCrawler;
import org.junit.Test;

import java.util.List;

public class TaduwenxueCrawlerTest {

    @Test
    public void test(){
        List<NovelInfo> list = TaduwenxueCrawler.create()
                .execute("http://www.tadu.com/store/131-a-1-5-a-20-p-1-122", 17);
        JsonUtils.printJson(list);
        NovelInfoHelper.saveAsExcel(list, "d:/taduwenxue.xlsx");
    }

}
