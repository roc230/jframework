package com.roc.jframeworkecharts.model.base;

import com.roc.jframework.basic.ext.HashMapExt;

import java.io.Serializable;

public class BasicFeature implements Serializable {
    private HashMapExt mark = new HashMapExt();
    private HashMapExt dataView = new HashMapExt();
    private HashMapExt magicType = new HashMapExt();
    private HashMapExt restore = new HashMapExt();
    private HashMapExt saveAsImage = new HashMapExt();

    public HashMapExt getMark() {
        return mark;
    }

    public void setMark(HashMapExt mark) {
        this.mark = mark;
    }

    public HashMapExt getDataView() {
        return dataView;
    }

    public void setDataView(HashMapExt dataView) {
        this.dataView = dataView;
    }

    public HashMapExt getMagicType() {
        return magicType;
    }

    public void setMagicType(HashMapExt magicType) {
        this.magicType = magicType;
    }

    public HashMapExt getRestore() {
        return restore;
    }

    public void setRestore(HashMapExt restore) {
        this.restore = restore;
    }

    public HashMapExt getSaveAsImage() {
        return saveAsImage;
    }

    public void setSaveAsImage(HashMapExt saveAsImage) {
        this.saveAsImage = saveAsImage;
    }
}
