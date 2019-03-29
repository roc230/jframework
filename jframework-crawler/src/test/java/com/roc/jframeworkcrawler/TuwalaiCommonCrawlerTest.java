package com.roc.jframeworkcrawler;

import com.roc.jframework.crawler.TuwalaiCommonCrawler;

public class TuwalaiCommonCrawlerTest {

    public static void main(String[] args){
        crawl1();
    }

    public static void crawl1(){
        String url = "http://www.tunwalai.com/story/233721/%E0%B8%AB%E0%B8%87%E0%B8%AA%E0%B9%8C%E0%B8%A5%E0%B9%88%E0%B8%A1%E0%B8%9A%E0%B8%B1%E0%B8%A5%E0%B8%A5%E0%B8%B1%E0%B8%87%E0%B8%81%E0%B9%8C?tdsourcetag=s_pcqq_aiomsg";
        TuwalaiCommonCrawler.create()
                .start(0)
                .max(40)
                .append(false)
                .execute(url);
    }
}
