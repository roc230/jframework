package com.roc.jframework.basic.utils;

import java.math.BigDecimal;
import java.util.List;

public class DoubleUtils {

    public static List<Integer> toInteger(List<Double> nums){
        List<Integer> list = ListUtils.newArrayList();
        for(Double num : nums){
            list.add(num.intValue());
        }
        return list;
    }

    public static List<Integer> toInteger(Double... nums){
        List<Integer> list = ListUtils.newArrayList();
        for(Double num : nums){
            list.add(num.intValue());
        }
        return list;
    }

    public static Integer toInteger(Double num){
        return num.intValue();
    }

    /**
     * 集合所有元素都乖以num得到新集合
     * @param list 集合
     * @param num 乖数
     * @param decimal 保留几位小数(四舍五入)
     * @return 新集合
     */
    public static List<Double> multiply(List<Double> list, Double num, int decimal){
        List<Double> newlist = ListUtils.newArrayList(list.size());
        for(Double x : list){
            Double v = BigDecimal.valueOf(x).multiply(BigDecimal.valueOf(num)).setScale(decimal).doubleValue();
            newlist.add(v);
        }
        return newlist;
    }

}
