package com.roc.jframework.web.webcrawler.dao;

import com.roc.jframework.web.bookmgr.dao.BaseRepository;
import com.roc.jframework.web.webcrawler.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryDAO extends BaseRepository<Category> {
}
