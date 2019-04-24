package com.roc.jframework.web.bookmgr.controller;

import com.roc.jframework.web.bookmgr.entity.Book;
import com.roc.jframework.web.bookmgr.service.IBookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(value = "/bookmgr")
@RestController
public class BookController {

    @Resource
    private IBookService bookService;

    @RequestMapping(value = "/get/{id}")
    @ResponseBody
    public Book get(@PathVariable("id") String id){
        Book book = this.bookService.get(id);
        return book;
    }
}
