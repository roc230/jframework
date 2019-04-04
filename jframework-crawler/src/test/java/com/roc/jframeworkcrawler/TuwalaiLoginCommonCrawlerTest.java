package com.roc.jframeworkcrawler;

import com.roc.jframework.crawler.TuwalaiCommonCrawler;
import com.roc.jframework.crawler.TuwalaiLoginCommonCrawler;

public class TuwalaiLoginCommonCrawlerTest {

    public static void main(String[] args){
//        crawl1();
//        crawl2();
//        crawl3();
//        crawl4();
        crawl5();
    }

    public static void crawl1(){
        String url = "http://www.tunwalai.com/story/233721/%E0%B8%AB%E0%B8%87%E0%B8%AA%E0%B9%8C%E0%B8%A5%E0%B9%88%E0%B8%A1%E0%B8%9A%E0%B8%B1%E0%B8%A5%E0%B8%A5%E0%B8%B1%E0%B8%87%E0%B8%81%E0%B9%8C?tdsourcetag=s_pcqq_aiomsg";
        TuwalaiCommonCrawler.create()
                .start(0)
                .max(40)
                .append(false)
                .execute(url);
    }

    public static void crawl2(){
        String url = "http://www.tunwalai.com/story/184179/%E0%B8%AA%E0%B8%B1%E0%B8%81%E0%B8%A7%E0%B8%B1%E0%B8%99%E0%B8%89%E0%B8%B1%E0%B8%99%E0%B8%88%E0%B8%B0%E0%B9%80%E0%B8%9B%E0%B9%87%E0%B8%99-%E0%B8%8B%E0%B8%B8%E0%B8%9B%E0%B8%95%E0%B8%B2%E0%B8%A3%E0%B9%8C";
        TuwalaiLoginCommonCrawler.create()
                .start(0)
                .max(10)
                .append(false)
                .login(true)
                .username("cuizhip@gmail.com")
                .password("czp840527")
                .execute(url);
    }

    public static void crawl3(){
        String url = "http://www.tunwalai.com/story/243492/%E0%B8%9B%E0%B8%A5%E0%B8%B8%E0%B8%81%E0%B8%AA%E0%B8%A7%E0%B8%A3%E0%B8%A3%E0%B8%84%E0%B9%8C%E0%B8%AA%E0%B8%A2%E0%B8%9A%E0%B8%9B%E0%B8%90%E0%B8%9E%E0%B8%B5";
        TuwalaiCommonCrawler.create()
                .start(0)
                .max(80)
                .append(false)
                .execute(url);
    }

    public static void crawl4(){
        String url = "http://www.tunwalai.com/story/258682/%E0%B8%A5%E0%B8%B4%E0%B8%82%E0%B8%B4%E0%B8%95%E0%B8%A3%E0%B8%B1%E0%B8%81%E0%B8%82%E0%B9%89%E0%B8%B2%E0%B8%A1%E0%B8%A0%E0%B8%9E\n";
        TuwalaiCommonCrawler.create()
                .start(0)
                .max(50)
                .append(false)
                .execute(url);
    }

    public static void crawl5(){
        String url = "http://www.tunwalai.com/story/251663/%E0%B9%80%E0%B8%AA%E0%B8%99%E0%B9%88%E0%B8%AB%E0%B8%B2%E0%B8%A2%E0%B8%B2%E0%B9%83%E0%B8%88";
        TuwalaiLoginCommonCrawler.create()
                .start(0)
                .max(57)
                .append(false)
                .execute(url);
    }
}
