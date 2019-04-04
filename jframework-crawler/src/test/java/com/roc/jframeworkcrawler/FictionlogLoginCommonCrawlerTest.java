package com.roc.jframeworkcrawler;

import com.roc.jframework.crawler.FictionlogCommonCrawler;
import com.roc.jframework.crawler.FictionlogLoginCommonCrawler;
import org.junit.Test;

public class FictionlogLoginCommonCrawlerTest {

    public static void main(String[] args){
//        task1();
//        tast2();
        FictionlogLoginCommonCrawlerTest t = new FictionlogLoginCommonCrawlerTest();
//        t.tast3();
//        t.task4();
        t.task5();
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

    public void task4(){
        FictionlogLoginCommonCrawler.create()
                .start(0)
                .max(40)
                .append(false)
                .execute("https://fictionlog.co/b/5a60d12567d832252ce8326e"); //
    }

    public void task5(){
        FictionlogLoginCommonCrawler.create()
                .start(0)
                .max(63)
                .append(false)
                .execute("https://fictionlog.co/b/5aaf8a878f8e480584499d4b");
    }
}
