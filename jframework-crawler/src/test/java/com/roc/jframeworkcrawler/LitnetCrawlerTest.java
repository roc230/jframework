package com.roc.jframeworkcrawler;

import com.roc.jframework.crawler.LitnetCrawler;
import org.junit.Test;

public class LitnetCrawlerTest {

    @Test
    public void aa(){
        String url = "https://litnet.com/en/book/someone-to-watch-over-me-b108738";
        LitnetCrawler crawler = new LitnetCrawler();
        crawler.execute(url);
    }

}
