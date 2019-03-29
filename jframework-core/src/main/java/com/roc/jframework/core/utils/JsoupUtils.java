package com.roc.jframework.core.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class JsoupUtils {

    public static Document parse(File file){
        try {
            return Jsoup.parse(file, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document parse(String html){
        return Jsoup.parse(html);
    }
}
