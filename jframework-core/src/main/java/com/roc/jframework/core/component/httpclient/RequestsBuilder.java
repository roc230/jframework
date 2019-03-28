package com.roc.jframework.core.component.httpclient;

import com.roc.jframework.basic.utils.InputStreamUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestsBuilder {
    private Map<String,String> params;
    private Map<String,String> headers;
    private String url;
    private String userAgent;

    //是否打印执行的url
    private Boolean showUrl = false;

    public RequestsBuilder() {
        this.params = new HashMap<>();
        this.headers = new HashMap<>();
    }

    public static RequestsBuilder create(){
        return new RequestsBuilder();
    }

    public Requests build(){
        Requests requests = new Requests();
        requests.setHeaders(headers);
        requests.setParams(params);
        requests.setUrl(url);
        requests.setUserAgent(userAgent);
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
}
