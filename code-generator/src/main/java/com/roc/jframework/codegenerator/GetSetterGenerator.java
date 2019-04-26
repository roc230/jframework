package com.roc.jframework.codegenerator;

import com.roc.jframework.basic.utils.StringUtils;

import java.lang.reflect.Field;

public class GetSetterGenerator {

    /**
     * 生成setter代码
     * @param field 域
     * @param margin 边距
     * @return
     */
    public static String getter(Field field, int margin){
        StringBuffer sb = new StringBuffer();
        sb.append(SpaceUtils.space(margin)).append("public ").append(field.getType().getSimpleName())
                .append(" get").append(StringUtils.uppercaseFirstChar(field.getName())).append("(){").append("\n")
                .append(SpaceUtils.space(margin + 4)).append("return this.").append(field.getName()).append(";\n")
                .append(SpaceUtils.space(margin)).append("}\n");
        return sb.toString();
    }

    /**
     * 生成getter方法代码
     * @param field 域
     * @param margin 边距
     * @return
     */
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
