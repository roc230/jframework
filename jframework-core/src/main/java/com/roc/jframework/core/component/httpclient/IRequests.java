package com.roc.jframework.core.component.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

public interface IRequests {

    IRequests header(String name, String value);
    IRequests param(String name, String value);
    IRequests userAgent(String userAgent);
    IRequests url(String url);
    CloseableHttpResponse get(Boolean showUrl);
    CloseableHttpResponse post();
}
