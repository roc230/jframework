package com.roc.jframeworkcrawler;

import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.Baiduyuedu;
import com.roc.jframework.crawler.IBaiduyuedu;
import org.junit.Test;

public class BaiduyueduTest {

    @Test
    public void test(){
        IBaiduyuedu baiduyuedu = new Baiduyuedu();
        Baiduyuedu.CopyRight cr = baiduyuedu.copyright("傅少宠妻夜夜来");
        if(cr == null){
            return ;
        }
        JsonUtils.printJson(cr);
    }
}
