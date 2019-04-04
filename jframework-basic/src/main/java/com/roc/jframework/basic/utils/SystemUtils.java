package com.roc.jframework.basic.utils;

/**
 * 操作系统工具类
 */
public class SystemUtils {

    /**
     * 获取操作系统名称
     * @return
     */
    public static String getOperationSystemName(){
        return System.getProperty("os.name");
    }

    /**
     * 是否Linux操作系统
     * @return
     */
    public static boolean isLinux(){
        String osname = getOperationSystemName();
        if(osname.toLowerCase().indexOf("linux") >= 0){
            return true;
        }
        return false;
    }

}
