package com.roc.jframework.core.component.httpclient;

import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.basic.utils.UrlUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

public class Requests implements IRequests{

    private Map<String,String> params;
    private Map<String,String> headers;
    private String url;
    private String userAgent;

    @Override
    public IRequests header(String name, String value) {
        return null;
    }

    @Override
    public IRequests param(String name, String value) {
        return null;
    }

    @Override
    public IRequests userAgent(String userAgent) {
        return null;
    }

    @Override
    public IRequests url(String url) {
        return null;
    }

    @Override
    public CloseableHttpResponse get(Boolean showUrl) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        //set userAgent
        if(!StringUtils.isNullOrEmpty(this.userAgent)){
            httpClientBuilder.setUserAgent(userAgent);
        }
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpRequestBase request = new HttpGet();

        //set url
        request.setURI(URI.create(this.url));

        //set header
        for(Map.Entry<String,String> entry : headers.entrySet()){
            Header h = new BasicHeader(entry.getKey(), entry.getValue());
            request.addHeader(h);
        }

        //set param
        String newUrl = UrlUtils.addParams(url, params);
        request.setURI(URI.create(newUrl));

        if(showUrl){
            System.out.println("url: " + newUrl);
        }

        try {
            CloseableHttpResponse r = httpClient.execute(request);
            if(r.getStatusLine().getStatusCode() == 200){
                return r;
            }else{
                System.err.println("请求异常，状态码：" + r.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

        return null;
    }

    @Override
    public CloseableHttpResponse post() {
        return null;
    }

    //////////


    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
