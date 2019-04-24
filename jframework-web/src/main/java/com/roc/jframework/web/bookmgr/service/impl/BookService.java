package com.roc.jframework.web.bookmgr.service.impl;

import com.roc.jframework.web.bookmgr.dao.IBookDAO;
import com.roc.jframework.web.bookmgr.entity.Book;
import com.roc.jframework.web.bookmgr.service.IBookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class BookService implements IBookService {

    @Resource
    private IBookDAO bookDAO;

    @Transactional
    @Override
    public void save(Book obj) {
        this.bookDAO.save(obj);
    }

    @Transactional
    @Override
    public void delete(String id) {
        this.bookDAO.deleteById(id);
    }

    @Transactional
    @Override
    public Book update(Book obj) {
        return this.bookDAO.saveAndFlush(obj);
    }

    @Transactional(readOnly = true)
    @Override
    public Book get(String id) {
        return this.bookDAO.findById(id).get();
    }
}
