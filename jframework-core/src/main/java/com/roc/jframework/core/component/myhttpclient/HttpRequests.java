package com.roc.jframework.core.component.myhttpclient;

import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.core.utils.InputStreamUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequests {

    private String methodName;

    private String url;

    private String userAgent;

    private Map<String,String> header = new HashMap<>();
    private Map<String,String> body = new HashMap<>();

    public static HttpRequests create(){
        return new HttpRequests();
    }

    public HttpRequests method(String method){
        this.methodName = method;
        return this;
    }

    public HttpRequests url(String url){
        this.url = url;
        return this;
    }

    public HttpRequests userAgent(String userAgent){
        header("User-Agent", userAgent);
        return this;
    }

    public HttpRequests header(String name, String value){
        if(header == null){
            header = new HashMap<>();
        }
        header.put(name, value);
        return this;
    }

    public HttpRequests body(String name, String value){
        if(body == null){
            body = new HashMap<>();
        }
        body.put(name, value);
        return this;
    }

    public HttpRequests contentType(String contentType){
        header("Content-Type", contentType);
        return this;
    }

    public HttpRequests acceptEncoding(String acceptEncoding){
        header("Accept-Encoding", acceptEncoding);
        return this;
    }

    public HttpResponse execute(){
        HttpClientBuilder builder  = HttpClientBuilder.create();
        if(!StringUtils.isNullOrEmpty(userAgent)){
            builder.setUserAgent(userAgent);
        }
        CloseableHttpClient httpClient = builder.build();
        if(StringUtils.isNullOrEmpty(methodName)){
            throw new RuntimeException("请设置请求方法: get or post");
        }
        HttpUriRequest request = null;

        if(methodName.equalsIgnoreCase("get")){
            request = new HttpGet();
            ((HttpGet) request).setURI(URI.create(url));
        }else if(methodName.equalsIgnoreCase("post")){
            request = new HttpPost();
            HttpPost post = (HttpPost)request;
            post.setURI(URI.create(url));
            //set body
            List<NameValuePair> pairList = new ArrayList<>();
            for(Map.Entry<String,String> entry : body.entrySet()){
                pairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            try {
                post.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            throw new RuntimeException("请求方法设置错误!");
        }

        //set header
        for(Map.Entry<String,String> entry : header.entrySet()){
            request.setHeader(entry.getKey(), entry.getValue());
        }

        //execute
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            closeableHttpResponse = httpClient.execute(request);
            InputStream content = closeableHttpResponse.getEntity().getContent();
            String str = InputStreamUtils.toString(content);

            HttpResponse httpResponse = new HttpResponse();
            httpResponse.setContent(str);
            return httpResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                closeableHttpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
