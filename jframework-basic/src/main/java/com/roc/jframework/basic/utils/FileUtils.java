package com.roc.jframework.basic.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    /**
     * 把文本保存为本地文件
     * @param content 文本内容
     * @param filepath 文件
     * @param cover 如果文件已存在，是否覆盖
     */
    public static void saveAsFile(String content, String filepath, boolean cover){

        try {
            File file = new File(filepath);

            if(file.exists() && cover == false){
                System.out.println("File is Exist! No write content to it.");
                return ;
            }

            //文件未创建
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveAsFile(String content, File file, boolean cover){
        try {
            if(file.exists() && cover == false){
                System.out.println("File is Exist! No write content to it.");
                return ;
            }

            //文件未创建
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
