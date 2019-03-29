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
                .url("https://www.novelhit.com/novels/6678/")
                .get();
        System.out.println(HttpClientUtils.getContent(r));
        HttpClientUtils.close(r);
    }
}
