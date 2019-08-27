package com.roc.jframeworkcrawler;

import com.roc.jframework.crawler.AllnovelCrawler;
import org.junit.Test;

public class AllnovelCrawlerTest {

    @Test
    public void test1(){
        String url = "https://allnovel.net/p-s-i-still-love-you-to-all-the-boys-i-ve-loved-before-2.html";
        AllnovelCrawler crawler = new AllnovelCrawler();
        crawler.execute(url);
    }

    @Test
    public void test2(){
        String url = "https://allnovel.net/tied-tangled-4.html";
        AllnovelCrawler crawler = new AllnovelCrawler();
        crawler.execute(url);
    }

    @Test
    public void test3(){
        String url = "https://allnovel.net/tamed-tangled-3.html";
        AllnovelCrawler crawler = new AllnovelCrawler();
        crawler.execute(url);
    }

    @Test
    public void test4(){
        String url = "https://allnovel.net/holy-frigging-matrimony-a-tangled-series-short-story-tangled-1-5.html";
        AllnovelCrawler crawler = new AllnovelCrawler();
        crawler.execute(url);
    }

    @Test
    public void test5(){
        String url = "https://allnovel.net/forever-you-forever-2.html";
        AllnovelCrawler crawler = new AllnovelCrawler();
        crawler.execute(url);
    }

    @Test
    public void test6(){
        String url = "https://allnovel.net/his-unlikely-lover-unwanted-3.html";
        AllnovelCrawler crawler = new AllnovelCrawler();
        crawler.execute(url);
    }

    @Test
    public void test7(){
        String url = "https://allnovel.net/the-edge-of-never-the-edge-of-never-1.html";
        AllnovelCrawler crawler = new AllnovelCrawler();
        crawler.execute(url);
    }

    @Test
    public void test8(){
        String url = "https://allnovel.net/ten-tiny-breaths-ten-tiny-breaths-1.html";
        AllnovelCrawler crawler = new AllnovelCrawler();
        crawler.execute(url);
    }

    @Test
    public void test9(){
        String url = "https://allnovel.net/losing-control-the-hunter-pact-1.html";
        AllnovelCrawler crawler = new AllnovelCrawler();
        crawler.execute(url);
    }

    @Test
    public void test10(){
        String url = "https://allnovel.net/the-unwanted-wife-unwanted-1.html";
        AllnovelCrawler crawler = new AllnovelCrawler();
        crawler.execute(url);
    }
}
