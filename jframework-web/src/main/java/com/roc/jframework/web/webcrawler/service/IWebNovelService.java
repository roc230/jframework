package com.roc.jframework.web.webcrawler.service;

import com.roc.jframework.web.webcrawler.entity.WebNovel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IWebNovelService {

    /**
     * 保存小说
     * @param novel 小说
     * @param categoryIds 题材id数组
     * @param novelSiteId 小说站点id
     * @return
     */
    WebNovel save(WebNovel novel, String[] categoryIds, String novelSiteId);

    WebNovel update(WebNovel novel);

    Page<WebNovel> getPage(Pageable pageable);

    WebNovel get(String id);

}
