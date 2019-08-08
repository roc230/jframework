package com.roc.jframework.web.entity;

import com.roc.jframework.basic.utils.ListUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用方法执行结果
 */
public class ExeResult<T> implements Serializable {
    /**
     * 执行结果编码
     */
    private int code;
    /**
     * 执行结果消息
     */
    private String msg;
    /**
     * 执行结果集合
     */
    List<T> list;
    /**
     * 单个执行结果
     */
    T obj;

    public static <T> ExeResult<T> of(int code, String msg, List<T> list, T obj){
        ExeResult<T> r = new ExeResult<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setList(list);
        r.setObj(obj);
        return r;
    }

    public static <T> ExeResult<T> of(int code, String msg, T obj){
        ExeResult<T> r = new ExeResult<>();
        r.setCode(code);
        r.setMsg(msg);
        List<T> list = new ArrayList<>(1);
        list.add(obj);
        r.setList(list);
        r.setObj(obj);
        return r;
    }

    public static <T> ExeResult<T> of(int code, String msg, List<T> list){
        ExeResult<T> r = new ExeResult<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setList(list);
        if(!ListUtils.isNullOrEmpty(list)){
            r.setObj(list.get(0));
        }
        return r;
    }

    public static <T> ExeResult<T> of(int code, String msg){
        ExeResult<T> r = new ExeResult<>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
