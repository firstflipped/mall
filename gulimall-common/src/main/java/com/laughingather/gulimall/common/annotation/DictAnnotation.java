package com.laughingather.gulimall.common.annotation;

import java.lang.annotation.*;

/**
 * 数据字典注解
 *
 * @author liujing
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DictAnnotation {

    /**
     * 字典code
     */
    String value();

}