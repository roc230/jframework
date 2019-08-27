package com.roc.jframework.basic.constants;

import java.io.Serializable;

public class ServiceResult<T> implements Serializable {
    private String code;
    private T result;

    public static <T> ServiceResult<T> of(ServiceResultCode code, T result){
        ServiceResult r = new ServiceResult();
        r.setCode(code.name());
        r.setResult(result);
        return r;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
