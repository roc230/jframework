package com.roc.jframework.codegenerator;

import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.basic.utils.ReflectionUtils;
import com.roc.jframework.basic.utils.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

public class BuilderGenerator {

    /*public String readSourceCode(String filepath) {
        String code = FileUtils.readAsString(filepath);
        code = removeFinalChar(code);
        System.out.println(code);
        return null;
    }
    public String addBuilderCode(String code){
        StringBuffer sb = new StringBuffer(code);
        sb.append("public static final class Builder{").append("\n")
                .append(SpaceUtils.space(4));
        return sb.toString();
    }

    public String removeFinalChar(String code){
        char[] chars = code.toCharArray();
        int index = -1;
        for(int i = chars.length - 1; i > 0; i--){
            if(chars[i] == '}'){
                index = i;
                break;
            }
        }
        return code.substring(0, index);
    }*/

    /**
     * 生成setter代码
     * @param field
     * @param margin
     * @return
     */
    private static String setter(Field field, int margin){
        StringBuffer sb = new StringBuffer();
        String type = field.getType().getSimpleName();
        String name = field.getName();
        sb.append(SpaceUtils.space(margin)).append("public Builder ").append(name).append("(").append(StringUtils.uppercaseFirstChar(type)).append(" ")
                .append(name).append("){").append("\n")
                .append(SpaceUtils.space(margin + 4)).append("this.").append(name).append(" = ").append(name).append(";\n")
                .append(SpaceUtils.space(margin + 4)).append("return this;\n")
                .append(SpaceUtils.space(margin)).append("}\n");
        return sb.toString();
    }

    /**
     * 生成字段代码
     * @param field
     * @param margin
     * @return
     */
    private static String field(Field field, int margin){
        String type = field.getType().getSimpleName();
        String name = field.getName();
        StringBuffer sb = new StringBuffer();
        sb.append(SpaceUtils.space(margin)).append("private ").append(type).append(" ").append(name).append(";\n");
        return sb.toString();
    }

    /**
     * 生成build()方法代码
     * @param clazz
     * @param margin
     * @return
     */
    private static String build(Class clazz, int margin){
        List<Field> fields = ReflectionUtils.getFields(clazz);
        String className = clazz.getSimpleName();

        StringBuffer sb = new StringBuffer();

        sb.append(SpaceUtils.space(margin)).append("public ").append(className).append(" build(){\n")
                .append(SpaceUtils.space(margin + 4)).append(className).append(" ").append(StringUtils.lowercaseFirstChar(className))
                .append(" = new ").append(className).append("();\n");

        for(Field f : fields){
            String type = f.getType().getSimpleName();
            String name = f.getName();
            sb.append(SpaceUtils.space(margin + 4)).append(StringUtils.lowercaseFirstChar(className))
                    .append(".set").append(StringUtils.uppercaseFirstChar(name)).append("(this.").append(name).append(");\n");
        }

        sb.append(SpaceUtils.space(margin + 4)).append("return ").append(StringUtils.lowercaseFirstChar(className))
                .append(";\n")
                .append(SpaceUtils.space(margin)).append("}\n");
        return sb.toString();
    }

    /**
     * 生成整个builder类代码
     * @param clazz 实体类
     * @param margin
     * @return
     */
    public static String builder(Class clazz, int margin){
        String className = clazz.getSimpleName();
        StringBuffer sb = new StringBuffer();
        sb.append(SpaceUtils.space(margin)).append("public static final class Builder{\n");

        List<Field> fields = ReflectionUtils.getFields(clazz);
        for(Field f : fields){
            String type = f.getType().getSimpleName();
            String name = f.getName();
            sb.append(SpaceUtils.space(margin + 4)).append("private ").append(type).append(" ").append(name).append(";\n");
        }

        for(Field f : fields){
            sb.append(setter(f, margin + 4));
        }

        sb.append(build(clazz, 8));

        sb.append(SpaceUtils.space(margin)).append("}\n");
        return sb.toString();
    }
}
