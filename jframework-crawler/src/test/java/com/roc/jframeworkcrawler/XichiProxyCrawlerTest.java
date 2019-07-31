package com.roc.jframeworkcrawler;

import com.roc.jframework.crawler.XichiProxyCrawler;
import org.junit.Test;

public class XichiProxyCrawlerTest {

    @Test
    public void test(){
        XichiProxyCrawler crawler = new XichiProxyCrawler();
        crawler.execute("https://www.xicidaili.com/nn");
    }
}
