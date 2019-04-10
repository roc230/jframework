package com.roc.jframeworkecharts.model.bar;

import java.io.Serializable;
import java.util.ArrayList;

public class Source extends ArrayList<Object> implements Serializable {

   public Source addE(Object obj){
       super.add(obj);
       return this;
   }

   public Source addE(Object... obj){
       for(Object o : obj){
           super.add(o);
       }
       return this;
   }
}
