package com.roc.jframeworkcrawler.novelweb;

import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.novelweb.NovelInfo;
import com.roc.jframework.crawler.novelweb.NovelInfoHelper;
import com.roc.jframework.crawler.novelweb.PingzhiwenxueCrawler;
import org.junit.Test;

import java.util.List;

public class PingzhiwenxueCrawlerTest {

    @Test
    public void test(){
        List<NovelInfo> list = PingzhiwenxueCrawler.create()
                .execute("http://wenxue.anysoft.cn/book/5-1-0-0-0", 78);
        JsonUtils.printJson(list);
        NovelInfoHelper.saveAsExcel(list, "d:/pingzhiwenxue.xlsx");
    }
}
