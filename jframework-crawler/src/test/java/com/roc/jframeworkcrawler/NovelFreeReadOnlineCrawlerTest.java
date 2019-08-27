package com.roc.jframeworkcrawler;

import com.roc.jframework.crawler.NovelFreeReadOnlineCrawler;
import org.junit.Test;

public class NovelFreeReadOnlineCrawlerTest {


    @Test
    public void test(){
        NovelFreeReadOnlineCrawler crawler = new NovelFreeReadOnlineCrawler();
        crawler.execute("http://novelfreereadonline.com/255081/fool-me-once#listchapter");
    }

    @Test
    public void test2(){
        NovelFreeReadOnlineCrawler crawler = new NovelFreeReadOnlineCrawler();
        crawler.execute("http://novelfreereadonline.com/255056/all-of-you");
    }

    @Test
    public void test3(){
        NovelFreeReadOnlineCrawler crawler = new NovelFreeReadOnlineCrawler();
        crawler.execute("http://novelfreereadonline.com/255060/there-you-stand");
    }

    @Test
    public void test4(){
        NovelFreeReadOnlineCrawler crawler = new NovelFreeReadOnlineCrawler();
        crawler.execute("http://novelfreereadonline.com/255040/sweet-little-lies");
    }


    @Test
    public void test5(){
        NovelFreeReadOnlineCrawler crawler = new NovelFreeReadOnlineCrawler();
        crawler.execute("http://novelfreereadonline.com/255059/promise-me-this");
    }

    @Test
    public void test6(){
        NovelFreeReadOnlineCrawler crawler = new NovelFreeReadOnlineCrawler();
        crawler.execute("http://novelfreereadonline.com/255057/before-you-break");
    }

    @Test
    public void test7(){
        NovelFreeReadOnlineCrawler crawler = new NovelFreeReadOnlineCrawler();
        crawler.execute("http://novelfreereadonline.com/255050/starry-eyes");
    }

    @Test
    public void test8(){
        NovelFreeReadOnlineCrawler crawler = new NovelFreeReadOnlineCrawler();
        crawler.execute("http://novelfreereadonline.com/255058/whisper-to-me");
    }

    @Test
    public void test9(){
        NovelFreeReadOnlineCrawler crawler = new NovelFreeReadOnlineCrawler();
        crawler.execute("http://novelfreereadonline.com/255004/dirty-little-secret");
    }

    @Test
    public void test10(){
        NovelFreeReadOnlineCrawler crawler = new NovelFreeReadOnlineCrawler();
        crawler.execute("http://novelfreereadonline.com/254997/filthy-beautiful-lies");
    }


}
