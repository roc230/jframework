package com.roc.jframework.web.webcrawler.service.impl;

import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.web.webcrawler.dao.ICategoryDAO;
import com.roc.jframework.web.webcrawler.dao.INovelSiteDAO;
import com.roc.jframework.web.webcrawler.dao.IWebNovelDAO;
import com.roc.jframework.web.webcrawler.entity.Category;
import com.roc.jframework.web.webcrawler.entity.NovelSite;
import com.roc.jframework.web.webcrawler.entity.WebNovel;
import com.roc.jframework.web.webcrawler.service.IWebNovelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WebNovelService extends BaseService implements IWebNovelService {

    @Resource
    private IWebNovelDAO webNovelDAO;
    @Resource
    private ICategoryDAO categoryDAO;
    @Resource
    private INovelSiteDAO novelSiteDAO;

    @Transactional
    @Override
    public WebNovel save(WebNovel novel, String[] categoryIds, String novelSiteId) {
        List<Category> categories = ListUtils.newArrayList();
        for(String cid : categoryIds){
            categories.add(this.categoryDAO.getOne(cid));
        }
        novel.setCategory(categories);
        NovelSite novelSite = this.novelSiteDAO.getOne(novelSiteId);
        novel.setNovelSite(novelSite);
        WebNovel novel1 = this.webNovelDAO.save(novel);
        this.webNovelDAO.flush();
        return novel1;
    }

    @Transactional
    @Override
    public WebNovel update(WebNovel novel) {
        return this.webNovelDAO.save(novel);
    }

    @Override
    public Page<WebNovel> getPage(Pageable pageable) {
        return this.webNovelDAO.findAll(pageable);
    }

    @Override
    public WebNovel get(String id) {
        if(StringUtils.isNullOrEmpty(id)){
            logger.info("小说ID不能为空");
            return null;
        }
        WebNovel novel = this.webNovelDAO.findById(id).get();
        if(novel == null){
            logger.info("没找到id[{}]的小说", id);
        }
        return novel;
    }
}
