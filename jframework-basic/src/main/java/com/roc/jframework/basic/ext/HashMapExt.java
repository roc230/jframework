package com.roc.jframework.basic.ext;

import java.util.HashMap;

public class HashMapExt<K extends Object,V extends Object> extends HashMap<K,V> {

    public HashMapExt<K,V> putE(K key, V value){
        super.put(key, value);
        return this;
    }
}
