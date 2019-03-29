package com.roc.jframeworkcrawler;

import com.roc.jframework.crawler.FictionlogCommonCrawler;

public class FictionlogCommonCrawlerTest {

    public static void main(String[] args){
        task1();
    }

    private static void task1(){
        FictionlogCommonCrawler.create()
                .start(0)
                .max(40)
                .append(false)
                .execute("https://fictionlog.co/b/5a461801a6b0cc4d4938174b");
    }
}
