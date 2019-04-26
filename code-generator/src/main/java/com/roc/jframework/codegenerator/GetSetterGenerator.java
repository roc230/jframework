package com.roc.jframework.codegenerator;

import com.roc.jframework.basic.utils.StringUtils;

import java.lang.reflect.Field;

public class GetSetterGenerator {

    public static String getter(Field field, int margin){
        StringBuffer sb = new StringBuffer();
        sb.append(SpaceUtils.space(margin)).append("public ").append(field.getType().getSimpleName())
                .append(" get").append(StringUtils.uppercaseFirstChar(field.getName())).append("(){").append("\n")
                .append(SpaceUtils.space(margin + 4)).append("return this.").append(field.getName()).append(";\n")
                .append(SpaceUtils.space(margin)).append("}\n");
        return sb.toString();
    }

    public static String setter(Field field, int margin){
        StringBuffer sb = new StringBuffer();
        String type = field.getType().getSimpleName();
        String name = field.getName();
        sb.append(SpaceUtils.space(margin)).append("public void set").append(StringUtils.uppercaseFirstChar(name)).append("(")
                .append(type).append(" ").append(name).append("){").append("\n")
                .append(SpaceUtils.space(margin+4)).append("this.").append(name).append(" = ").append(name).append(";\n")
                .append(SpaceUtils.space(margin)).append("}\n");
        return sb.toString();
    }
}
