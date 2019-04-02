package com.roc.jframework.basic.utils;

public class TimerUtils {

    /**
     * 让当前线程暂停指定时间
     * @param miliseconds
     */
    public static void sleep(long miliseconds){
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
