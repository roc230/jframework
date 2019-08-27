package com.roc.jframeworkcrawler;

import com.roc.jframework.crawler.PublicBookshelfCrawler;
import org.junit.Test;

public class PublicBookshelfCrawlerTest {

    @Test
    public void test(){
        String url = "http://www.publicbookshelf.com/contemporary/court-recluse/";
        PublicBookshelfCrawler crawler = new PublicBookshelfCrawler();
        crawler.execute(url);
    }

    @Test
    public void test2(){
        String url = "http://www.publicbookshelf.com/contemporary/missing-heiress/";
        PublicBookshelfCrawler crawler = new PublicBookshelfCrawler();
        crawler.execute(url);
    }

    @Test
    public void test3(){
        String url = "http://www.publicbookshelf.com/romantic-suspense/wallflower-girl/";
        PublicBookshelfCrawler crawler = new PublicBookshelfCrawler();
        crawler.execute(url);
    }




}
