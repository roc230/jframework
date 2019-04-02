package com.roc.jframework.core;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.core.component.httpclient.HttpClientUtils;
import com.roc.jframework.core.component.httpclient.RequestsBuilder;
import org.apache.http.HttpResponse;
import org.junit.Test;

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
                .url("https://www.kawebook.com/story/97/1988/%E0%B8%AD%E0%B9%88%E0%B8%B2%E0%B8%99%E0%B8%99%E0%B8%B4%E0%B8%A2%E0%B8%B2%E0%B8%A2/%E0%B9%80%E0%B8%88%E0%B9%89%E0%B8%B2%E0%B8%AA%E0%B8%B2%E0%B8%A7%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B9%83%E0%B8%AB%E0%B8%A1%E0%B9%88%E0%B9%81%E0%B8%AB%E0%B9%88%E0%B8%87%E0%B8%AA%E0%B8%81%E0%B8%A5%E0%B8%A5%E0%B8%B9%E0%B9%88/%E0%B9%80%E0%B8%A5%E0%B9%88%E0%B8%A1%E0%B8%97%E0%B8%B5%E0%B9%881-%E0%B8%9A%E0%B8%97%E0%B8%97%E0%B8%B5%E0%B9%88-1-%E0%B9%81%E0%B8%95%E0%B9%88%E0%B8%87%E0%B8%87%E0%B8%B2%E0%B8%99%E0%B9%81%E0%B8%A5%E0%B9%89%E0%B8%A7%E0%B8%88%E0%B9%89%E0%B8%B2")
                .get();
        System.out.println(HttpClientUtils.getContent(r));
        HttpClientUtils.close(r);
    }

    @Test
    public void testpost(){
        HttpResponse r = RequestsBuilder.create()
                .param("username", "roc")
                .param("password", "123")
                .url("http://localhost:8080/test/post")
                .post();
        String c = HttpClientUtils.getContent(r);
        HttpClientUtils.close(r);
        System.out.println(c);
    }

    @Test
    public void testProxy(){

        String url = "http://localhost:8080/test/hello";

        HttpResponse r = RequestsBuilder.create()
                .url(url)
                .proxy("localhost", 8888, "http")
                .get();
    }

    @Test
    public void testLogin(){
        String url = "https://accounts.ookbee.com/LogIn/AuthenticationUser";
        HttpResponse r = RequestsBuilder.create()
                .param("UserName", "cuizhip@gmail.com")
                .param("Password", "czp840527")
                .param("AppCode", "TUNWALAI_209")
                .url(url)
                .post();
        if(r.getStatusLine().getStatusCode() == 302){
            String location = r.getFirstHeader("Location").getValue();
            System.out.println("redirect location: " + location);
        }
        String c = HttpClientUtils.getContent(r);
        HttpClientUtils.close(r);
        System.out.println(c);
    }
}
