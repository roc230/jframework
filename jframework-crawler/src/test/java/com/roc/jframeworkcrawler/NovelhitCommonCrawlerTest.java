package com.roc.jframeworkcrawler;

import com.roc.jframework.crawler.NovelhitCommonCrawler;
import org.junit.Test;

public class NovelhitCommonCrawlerTest {

    @Test
    public void test(){
        NovelhitCommonCrawler.create()
                .execute("https://www.novelhit.com/novel/6663/?tdsourcetag=s_pcqq_aiomsg");
    }
}
