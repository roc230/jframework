package com.roc.jframework.core.component.httpclient;

import com.roc.jframework.basic.utils.InputStreamUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

public class HttpClientUtils {
    /**
     * 关闭请求
     * @param response
     */
    public static void close(HttpResponse response){
        if(response == null){
            return ;
        }
        if(response instanceof CloseableHttpResponse){
            try {
                ((CloseableHttpResponse)response).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取返回结果字符串
     * @param response
     * @return
     */
    public static String getContent(HttpResponse response){
        try {
            return InputStreamUtils.readToString(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
