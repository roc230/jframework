package com.roc.jframework.core.component.httpclient;

import com.roc.jframework.basic.utils.MapUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.basic.utils.UrlUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Requests extends AbstractRequests{

    @Override
    public CloseableHttpResponse get(Boolean showUrl) {

        HttpClientBuilder httpClientBuilder = null;
        if(url.startsWith("http")){
            httpClientBuilder = HttpClients
                    .custom()
                    .setDefaultCookieStore(new BasicCookieStore())  //set cookie store
                    .setRedirectStrategy(new LaxRedirectStrategy()); //set redirect strategy
        }else if(url.startsWith("https")){
            SSLConnectionSocketFactory factory = buildSSLConnectionFactory();
            PoolingHttpClientConnectionManager connPool = buildSSLConnectionPool(factory);

            httpClientBuilder = HttpClients
                    .custom()
                    .setConnectionManagerShared(true)
                    .setConnectionManager(connPool)
                    .setSSLSocketFactory(factory)
                    .setDefaultCookieStore(new BasicCookieStore())  //set cookie store
                    .setRedirectStrategy(new LaxRedirectStrategy()); //set redirect strategy
        }


        //set userAgent
        if(!StringUtils.isNullOrEmpty(this.userAgent)){
            httpClientBuilder.setUserAgent(userAgent);
        }

        //set proxy
        if(httpHost != null){
            httpClientBuilder.setProxy(httpHost);
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

        HttpClientBuilder httpClientBuilder = null;

        if(url.startsWith("http")){
            httpClientBuilder = HttpClients.custom()
                    .setDefaultCookieStore(new BasicCookieStore())
                    .setRedirectStrategy(new LaxRedirectStrategy());
        }else if(url.startsWith("https")){
            SSLConnectionSocketFactory factory = buildSSLConnectionFactory();
            PoolingHttpClientConnectionManager connPool = buildSSLConnectionPool(factory);

            httpClientBuilder = HttpClients
                    .custom()
                    .setConnectionManagerShared(true)
                    .setConnectionManager(connPool)
                    .setSSLSocketFactory(factory)
                    .setDefaultCookieStore(new BasicCookieStore())  //set cookie store
                    .setRedirectStrategy(new LaxRedirectStrategy()); //set redirect strategy
        }


        //set userAgent
        if(!StringUtils.isNullOrEmpty(this.userAgent)){
            httpClientBuilder.setUserAgent(userAgent);
        }

        //set proxy
        if(httpHost != null){
            httpClientBuilder.setProxy(httpHost);
        }

        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpPost request = new HttpPost();
        //set url
        request.setURI(URI.create(url));
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

        //set request body
        if(!MapUtils.isNullOrEmpty(params)){
            List<NameValuePair> pair = new ArrayList<>();
            for(Map.Entry<String,String> entry : params.entrySet()){
                NameValuePair p = new BasicNameValuePair(entry.getKey(), entry.getValue());
                pair.add(p);
            }
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pair);
                request.setEntity(entity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
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
