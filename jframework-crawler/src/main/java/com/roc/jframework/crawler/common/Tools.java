package com.roc.jframework.crawler.common;

import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Novel;

import java.io.File;

public class Tools {

    public static void saveAsMultiTxt(Novel novel, File file, int chapterCountPerPage){
        int count = chapterCountPerPage;//每页保存多少章内容
        int pnum = novel.getChapters().size() / count + 1;
        if(novel.getChapters().size() % count == 0){
            pnum = novel.getChapters().size() / count;
        }

        for(int i = 0; i < pnum; i++){
            StringBuffer sb = new StringBuffer();
            //如果是最后一页
            if((i + 1) == pnum){
                for(int j = i*count; j < novel.getChapters().size(); j++){
                    Chapter chapter = novel.getChapters().get(j);
                    String name = chapter.getTitle();
                    sb.append("\r\n")
                            .append(name)
                            .append("\r\n")
                            .append("\r\n");
                    for(String p : chapter.getParagraphs()){
                        sb.append("\0\0\0\0\0\0\0\0")
                                .append(p)
                                .append("\r\n")
                                .append("\r\n");
                    }
                }
            }else{//非最后一页
                for(int j = i*count; j < (i+1)*count; j++){
                    Chapter chapter = novel.getChapters().get(j);
                    String name = chapter.getTitle();
                    sb.append("\r\n")
                            .append(name)
                            .append("\r\n")
                            .append("\r\n");
                    for(String p : chapter.getParagraphs()){
                        sb.append("\0\0\0\0\0\0\0\0")
                                .append(p)
                                .append("\r\n")
                                .append("\r\n");
                    }
                }
            }

            String filename = file.getAbsolutePath().replace(".txt", "");
            filename = filename + "-p" + i + ".txt";
            FileUtils.saveAsFile(sb.toString(), new File(filename), true);
        }
    }

    public static void saveAsSigleTxt(Novel novel, File file){

    }

    public static void saveAsJson(Novel novel, File file){
        FileUtils.saveAsFile(JsonUtils.toString(novel), file, true);
    }

    public static Novel readFromJson(String filepath){
        String json = FileUtils.readAsString(filepath);
        if(StringUtils.isNullOrEmpty(json)){
            return null;
        }
        return JsonUtils.fromJson(json, Novel.class);
    }
}
