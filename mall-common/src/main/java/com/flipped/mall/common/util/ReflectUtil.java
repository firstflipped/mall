package com.flipped.mall.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反射工具类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-07-25 15:55:39
 */
public class ReflectUtil {

    /**
     * 获取类的所有属性，包括父类
     *
     * @param object 对象
     * @return 属性数组
     */
    public static Field[] getAllFields(Object object) {
        Class<?> clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

}
