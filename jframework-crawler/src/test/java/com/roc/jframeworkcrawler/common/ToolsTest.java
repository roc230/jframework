package com.roc.jframeworkcrawler.common;

import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.crawler.common.Tools;
import com.roc.jframework.crawler.entity.Novel;
import org.junit.Test;

import java.io.File;

public class ToolsTest {

    @Test
    public void test_jsonToSingleTxt(){
        String filepath = "D:\\novel1\\public-bookshelf\\Wallflower Girl.json";
        Novel novel = Tools.readFromJson(filepath);
        if(filepath.contains("\\")){
            String savefile = filepath.substring(0, filepath.lastIndexOf("\\")) + "\\" + novel.getName() + ".single.txt";
            Tools.saveAsMultiTxt(novel, new File(savefile), 10000);
        }else if(filepath.contains("/")){
            String savefile = filepath.substring(0, filepath.lastIndexOf("/")) + "/" + novel.getName() + ".single.txt";
            Tools.saveAsMultiTxt(novel, new File(savefile), 10000);
        }

    }
}
