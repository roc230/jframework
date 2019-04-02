package com.roc.jframework.core.component.httpclient;

import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.basic.utils.UrlUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class Requests implements IRequests{

    private Map<String,String> params;
    private Map<String,String> headers;
    private String url;
    private String userAgent;
    private String charset;
    private String contentType;
    private String accept;
    private String acceptEncoding;
    private String acceptLanguage;
    private String referer;
    private String xRequestedWith;

    public Requests(){
        params = new HashMap<>();
        headers = new HashMap<>();
    }

    @Override
    public IRequests header(String name, String value) {
        if(!StringUtils.isNullOrEmpty(name)){
            headers.put(name, value);
        }
        return this;
    }

    @Override
    public IRequests param(String name, String value) {
        if(!StringUtils.isNullOrEmpty(name)){
            params.put(name, value);
        }
        return this;
    }

    @Override
    public IRequests userAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    @Override
    public IRequests url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public IRequests charset(String charset) {
        this.charset = charset;
        return this;
    }

    @Override
    public IRequests accept(String accept) {
        this.accept = accept;
        return this;
    }

    @Override
    public IRequests acceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
        return this;
    }

    @Override
    public IRequests acceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
        return this;
    }

    @Override
    public IRequests contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    @Override
    public IRequests referer(String referer) {
        this.referer = referer;
        return this;
    }

    @Override
    public IRequests xRequestedWith(String xRequestedWith) {
        this.xRequestedWith = xRequestedWith;
        return this;
    }

    @Override
    public CloseableHttpResponse get(Boolean showUrl) {
        HttpClientBuilder httpClientBuilder = HttpClients
                .custom()
                .setDefaultCookieStore(new BasicCookieStore())  //set cookie store
                .setRedirectStrategy(new LaxRedirectStrategy()); //set redirect strategy

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

        //set Content-Type
        if(contentType != null){
            request.addHeader("content-type", contentType);
        }

        //set referer
        if(referer != null){
            request.addHeader("referer", referer);
        }

        //set charset
        if(charset != null){
            request.addHeader("charset", charset);
        }

        //set accept
        if(accept != null){
            request.addHeader("accept", accept);
        }

        //set acceptLanguage
        if(acceptLanguage != null){
            request.addHeader("accept-language", accept);
        }

        //set accept encoding
        if(acceptEncoding != null){
            request.addHeader("accept-encoding", acceptEncoding);
        }

        //set x-requested-with
        if(xRequestedWith != null){
            request.addHeader("x-requested-with", xRequestedWith);
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
        throw new UnsupportedOperationException();
    }

    //////////


    public Map<String, String> getParams() {
        return params;
    }


    public Map<String, String> getHeaders() {
        return headers;
    }


    public String getUrl() {
        return url;
    }


    public String getUserAgent() {
        return userAgent;
    }


    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getContentType() {
        return contentType;
    }


    public String getAccept() {
        return accept;
    }


    public String getAcceptEncoding() {
        return acceptEncoding;
    }


    public String getAcceptLanguage() {
        return acceptLanguage;
    }


    public String getReferer() {
        return referer;
    }


    public String getxRequestedWith() {
        return xRequestedWith;
    }

}
