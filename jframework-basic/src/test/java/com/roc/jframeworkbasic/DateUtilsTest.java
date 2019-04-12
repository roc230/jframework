package com.roc.jframeworkbasic;

import com.roc.jframework.basic.utils.DateUtils;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class DateUtilsTest {

    @Test
    public void test(){
        Date date = new Date();
        List<Date> days = DateUtils.previousDays(date, 3);
        DateUtils.printDates(days.toArray(new Date[0]));

    }
}
