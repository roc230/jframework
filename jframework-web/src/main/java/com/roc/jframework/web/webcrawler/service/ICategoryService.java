package com.roc.jframework.web.webcrawler.service;

import com.roc.jframework.web.webcrawler.entity.Category;

public interface ICategoryService {

    Category save(Category category);

    Category get(String id);
}
