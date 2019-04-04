package com.roc.jframeworkcrawler;

import com.roc.jframework.crawler.FictionlogCommonCrawler;
import com.roc.jframework.crawler.FictionlogLoginCommonCrawler;
import org.junit.Test;

public class FictionlogLoginCommonCrawlerTest {

    public static void main(String[] args){
//        task1();
//        tast2();
        FictionlogLoginCommonCrawlerTest t = new FictionlogLoginCommonCrawlerTest();
        t.tast3();
    }

    @Test
    public void task1(){
        FictionlogLoginCommonCrawler.create()
                .start(0)
                .max(40)
                .append(false)
                .execute("https://fictionlog.co/b/5a461801a6b0cc4d4938174b");
    }

    @Test
    public void tast2(){
        FictionlogLoginCommonCrawler.create()
                .start(0)
                .max(50)
                .append(false)
                .execute("https://fictionlog.co/b/5a450bfdccd9a94d648f9718");
    }

    @Test
    public void tast3(){
        FictionlogLoginCommonCrawler.create()
                .start(0)
                .max(40)
                .append(false)
                .login(true)
                .username("roc230")
                .password("czp840527")
                .execute("https://fictionlog.co/b/5a452dc7a6b0cc4d4937e53a");
    }
}
