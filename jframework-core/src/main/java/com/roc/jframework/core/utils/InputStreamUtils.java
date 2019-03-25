package com.roc.jframework.core.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamUtils {

    public static String toString(InputStream inputStream){
        try {
            return IOUtils.toString(inputStream,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
