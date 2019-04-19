package com.roc.jframeworkbasic;

import com.roc.jframework.basic.utils.DateUtils;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtilsTest {

    @Test
    public void test(){
        Date date = new Date();
        List<Date> days = DateUtils.previousDays(date, 3);
        DateUtils.printDates(days.toArray(new Date[0]));

    }

    @Test
    public void test1(){
        Calendar c = Calendar.getInstance();
        Date d = DateUtils.buildDate(2019, 4, 15, 0, 0, 0, 0);
        System.out.println(d.getTime());
    }
}
