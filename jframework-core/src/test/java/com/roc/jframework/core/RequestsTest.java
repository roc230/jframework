package com.roc.jframework.core;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.InputStreamUtils;
import com.roc.jframework.core.component.httpclient.HttpClientUtils;
import com.roc.jframework.core.component.httpclient.RequestsBuilder;
import org.apache.http.HttpResponse;
import org.junit.Test;

import java.io.IOException;

public class RequestsTest {

    @Test
    public void test(){
        HttpResponse r = RequestsBuilder.create()
//                .header("a","111")
//                .header("b","222")
//                .param("x", "123")
//                .param("y", "433")
                .userAgent(UserAgent.CHROME)
//                .showUrl(true)
                .url("https://www.kawebook.com/story/97/%E0%B8%99%E0%B8%B4%E0%B8%A2%E0%B8%B2%E0%B8%A2/%E0%B8%99%E0%B8%B4%E0%B8%A2%E0%B8%B2%E0%B8%A2-%E0%B8%A3%E0%B8%B1%E0%B8%81/%E0%B9%80%E0%B8%88%E0%B9%89%E0%B8%B2%E0%B8%AA%E0%B8%B2%E0%B8%A7%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B9%83%E0%B8%AB%E0%B8%A1%E0%B9%88%E0%B9%81%E0%B8%AB%E0%B9%88%E0%B8%87%E0%B8%AA%E0%B8%81%E0%B8%A5%E0%B8%A5%E0%B8%B9%E0%B9%88")
                .get();
        System.out.println(HttpClientUtils.getContent(r));
        HttpClientUtils.close(r);
    }
}
