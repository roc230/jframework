package com.roc.jframeworkbasic;

import com.roc.jframework.basic.component.dateconverter.SolarLunarConverter;
import org.junit.Test;

public class SolarLunarConverterTest {

    @Test
    public void test(){
        SolarLunarConverter.Solar solar = new SolarLunarConverter.Solar();
        solar.solarYear = 1984;
        solar.solarMonth = 6;
        solar.solarDay = 26;
        SolarLunarConverter.Lunar lunar = SolarLunarConverter.SolarToLunar(solar);
        System.out.println(lunar.toString());
        SolarLunarConverter.Solar s = SolarLunarConverter.LunarToSolar(lunar);
        System.out.println(s.toString());

        System.out.println(SolarLunarConverter.lunarYearToGanZhi(lunar.lunarYear));

    }
}
