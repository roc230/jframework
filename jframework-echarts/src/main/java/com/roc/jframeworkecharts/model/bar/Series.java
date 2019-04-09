package com.roc.jframeworkecharts.model.bar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Series extends ArrayList<HashMap<String,String>> implements Serializable {

    public Series add(String key, String value){
        HashMap<String,String> map = new HashMap<>();
        map.put(key, value);
        this.add(map);
        return this;
    }

}
