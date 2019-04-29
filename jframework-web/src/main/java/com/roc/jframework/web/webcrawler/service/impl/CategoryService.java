package com.roc.jframework.web.webcrawler.service.impl;

import com.roc.jframework.web.webcrawler.dao.ICategoryDAO;
import com.roc.jframework.web.webcrawler.entity.Category;
import com.roc.jframework.web.webcrawler.service.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CategoryService implements ICategoryService {

    @Resource
    private ICategoryDAO categoryDAO;

    @Transactional
    @Override
    public Category save(Category category) {
       return this.categoryDAO.saveAndFlush(category);
    }

    @Transactional(readOnly = true)
    @Override
    public Category get(String id) {
        return this.categoryDAO.findById(id).get();
    }
}
