package com.roc.jframework.basic.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static final String PATTERN_1 = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String PATTERN_2 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 按格式获取日期字符串
     * @param date 日期对象
     * @param pattern 日期格式
     * @return
     */
    public static String getString(Date date, String pattern){
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static List<String> getString(List<Date> dates, String pattern){
        List<String> list = ListUtils.newArrayList();
        for(Date d : dates){
            list.add(getString(d, pattern));
        }
        return list;
    }

    /**
     * 创建指定时间对象
     * @param year 年
     * @param month 月
     * @param day 日
     * @param hour 时
     * @param minute 分
     * @param seconds 秒
     * @param milliseconds 微秒
     * @return
     */
    public static Date buildDate(int year, int month, int day, int hour, int minute, int seconds, int milliseconds){
        Calendar car = Calendar.getInstance();
        car.set(Calendar.YEAR, year);
        car.set(Calendar.MONTH, month);
        car.set(Calendar.DAY_OF_MONTH, day);
        car.set(Calendar.HOUR_OF_DAY, hour);
        car.set(Calendar.MINUTE, minute);
        car.set(Calendar.SECOND, seconds);
        car.set(Calendar.MILLISECOND, milliseconds);
        return car.getTime();
    }

    /**
     * 获取指定日期往后多少天的日期
     * @param date
     * @param interval
     * @return
     */
    public static Date nextDay(Date date, int interval){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, interval);
        return cal.getTime();
    }

    /**
     * 获取指定日期之前多少天的日期
     * @param date
     * @param interval
     * @return
     */
    public static Date previousDay(Date date, int interval){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -interval);
        return cal.getTime();
    }

    /**
     * 获取指定日期往后数天的日期集合(包含指定的日期)
     * @param date
     * @param interval 日期间隔，大于零
     * @return
     */
    public static List<Date> nextDays(Date date, int interval){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        List<Date> list = new ArrayList<>();
        list.add(date);
        for(int i = 1; i < interval; i++){
            cal.add(Calendar.DAY_OF_MONTH, 1);
            list.add(cal.getTime());
        }
        return list;
    }

    /**
     * 获取指定日期往前数天的日期集合(包含指定日期)
     * @param date 指定日期
     * @param interval 日期区间
     * @return
     */
    public static List<Date> previousDays(Date date, int interval){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        List<Date> list = new ArrayList<>();
        list.add(date);
        for(int i = 1; i < interval; i++){
            cal.add(Calendar.DAY_OF_MONTH, -1);
            list.add(cal.getTime());
        }
        List<Date> reverse = ListUtils.reverse(list);
        list.clear();
        list = null;
        return reverse;
    }

    /**
     * 日期输出控制台
     * @param dates
     */
    public static void printDates(Date... dates){
        for(Date date : dates){
            System.out.println(getString(date, PATTERN_1));
        }
    }

}
