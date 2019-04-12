package com.roc.jframeworkecharts.model.line;

import java.io.Serializable;

public class ItemStyle implements Serializable {

   private Normal normal;

   public ItemStyle(){}

   public ItemStyle(Normal normal){
       this.normal = normal;
   }

    public Normal getNormal() {
        return normal;
    }

    public void setNormal(Normal normal) {
        this.normal = normal;
    }
}
