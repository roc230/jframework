package com.roc.jframework.core;


import com.roc.jframework.core.component.myhttpclient.HttpRequests;
import com.roc.jframework.core.component.myhttpclient.HttpResponse;
import org.junit.Test;

public class HttpClientTest {

    @Test
    public void test(){
        String url = "https://fictionlog.co/c/5c80da905c8ef5fde886e838";
        HttpResponse r = HttpRequests.create()
                .url(url)
                .method("get")
                .header("Content-Type", "application/json")
//                .userAgent("robot")
                .execute();
        System.out.println(r.getContent());
    }
}
