package com.roc.jframeworkbasic;

import com.roc.jframework.basic.utils.StringUtils;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void test1(){
        String txt = "ฉิงเฟิงเอาเข็มสีเงิน 9 เข็มออกมาและใช้ [9 เข็มแห่งโชคชะตา] ทันทีฝังลงที่จุด shen que, guan yuan (มันคือจุดตรงสะดือและใต้สะดือ ตามลิ้ง https://www.google.co.th/search?q=shen+que&amp;rlz=1C1CHWL_enTH758TH758&amp;source=lnms&amp;tbm=isch&amp;sa=X&amp;ved=0ahUKEwjz2-udp6rbAhWWWysKHVhsCKoQ_AUICigB&amp;biw=2133&amp;bih=1054#imgrc=67c9rCiPQGC_9M:) ตั้งแต่ถูกฝังเข็มมือของฉิงเฟิงย่อมสัมผัสร่างของหวานชิว";
        String target = StringUtils.findByReg(txt, "\\(.+www\\.google\\.co.+\\)", 0);
        System.out.println(target);
    }
}
