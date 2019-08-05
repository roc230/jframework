package com.roc.jframework.core.component.myhttpclient;

import com.roc.jframework.core.utils.JsoupUtils;
import org.jsoup.nodes.Document;

public class HttpResponse {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Document asJsoupDocument(){
        Document doc = JsoupUtils.parse(this.content);
        return doc;
    }
}
