package com.roc.jframeworkcrawler.novelcopyright;

import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.novelcopyright.NovelSearch;
import com.roc.jframework.crawler.novelcopyright.ZhangyueSearch;
import org.junit.Test;

import java.util.List;

public class ZhangyueSearchTest {

    @Test
    public void test(){
        List<NovelSearch> list = ZhangyueSearch.execute("你给的爱凉凉的");
        JsonUtils.printJson(list);
    }
}
