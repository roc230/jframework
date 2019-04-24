package com.roc.jframework.web.bookmgr.service;

public interface BaseService<T> {

    void save(T obj);

    void delete(String id);

    T update(T obj);

    T get(String id);

}
