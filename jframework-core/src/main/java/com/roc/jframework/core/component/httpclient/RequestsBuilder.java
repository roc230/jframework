package com.roc.jframework.core.component.httpclient;

import com.roc.jframework.basic.utils.InputStreamUtils;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestsBuilder {
    private Map<String,String> params;
    private Map<String,String> headers;
    private String url;
    private String userAgent;
    private String charset = "utf-8";
    private String contentType;
    private String accept;
    private String acceptLanguage;
    private String acceptEncoding;
    private String referer;
    private String xRequestedWith;

    //是否打印执行的url
    private Boolean showUrl = false;

    public RequestsBuilder() {
        this.params = new HashMap<>();
        this.headers = new HashMap<>();
    }

    public static RequestsBuilder create(){
        return new RequestsBuilder();
    }

    /**
     * 创建Requests对象实例
     * @return
     */
    public Requests build(){
        Requests requests = new Requests();
        requests.url(url)
                .userAgent(userAgent)
                .acceptEncoding(acceptEncoding)
                .accept(accept)
                .acceptLanguage(acceptLanguage)
                .contentType(contentType)
                .charset(charset)
                .referer(referer)
                .xRequestedWith(xRequestedWith);
        for(Map.Entry<String,String> h : headers.entrySet()){
            requests.header(h.getKey(), h.getValue());
        }
        for(Map.Entry<String,String> p : params.entrySet()){
            requests.param(p.getKey(), p.getValue());
        }
        return requests;
    }

    public CloseableHttpResponse get(){
        Requests requests = build();
        return requests.get(showUrl);
    }

    /**
     * 把返回内容读为字符串
     * @return
     */
    public String getAsString(){
        CloseableHttpResponse r = get();
        if(r != null){
            try {
                String str = InputStreamUtils.readToString(r.getEntity().getContent());
                HttpClientUtils.close(r);
                return str;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

   public RequestsBuilder header(String name, String value){
        this.headers.put(name, value);
        return this;
   }

   public RequestsBuilder param(String name, String value){
        this.params.put(name, value);
        return this;
   }

   public RequestsBuilder userAgent(String userAgent){
        this.userAgent = userAgent;
        return this;
   }

   public RequestsBuilder url(String url){
        this.url = url;
        return this;
   }

   public RequestsBuilder showUrl(Boolean showUrl){
        this.showUrl = showUrl;
        return this;
   }

   public RequestsBuilder charset(String charset){
        this.charset = charset;
        return this;
   }

   public RequestsBuilder contentType(String contentType){
        this.contentType = contentType;
        return this;
   }

   public RequestsBuilder accept(String accept){
        this.accept = accept;
        return this;
   }

   public RequestsBuilder acceptLanguage(String acceptLanguage){
        this.acceptLanguage = acceptLanguage;
        return this;
   }

   public RequestsBuilder acceptEncoding(String accepEncoding){
        this.acceptEncoding = accepEncoding;
        return this;
   }

   public RequestsBuilder referer(String referer){
        this.referer = referer;
        return this;
   }

   public RequestsBuilder xRequestedWith(String xRequestedWith){
        this.xRequestedWith = xRequestedWith;
        return this;
   }
}
