package com.roc.jframework.basic.utils;

import java.io.*;

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

    /**
     * 保存文件
     * @param content
     * @param file
     * @param cover
     */
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

    /**
     * 读文件内容到StringBuffer
     * @param buffer StringBuffer
     * @param filepath 文件路径
     */
    public static void readToBuffer(StringBuffer buffer, String filepath){
        try {
            InputStream inputStream = new FileInputStream(filepath);
            String line = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            line = reader.readLine();
            while(line != null){
                buffer.append(line)
                        .append("\n");
                line = reader.readLine();
            }
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把文件读出为字符串
     * @param filepath 文件路径
     * @return
     */
    public static String readAsString(String filepath){
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, filepath);
        return sb.toString();
    }
}
