package com.roc.jframework.basic.exception;

/**
 * 方法参数值错误异常
 */
public class ParamValueErrorException extends RuntimeException {

    public ParamValueErrorException(){}

    public ParamValueErrorException(String exception){
        super(exception);
    }
}
