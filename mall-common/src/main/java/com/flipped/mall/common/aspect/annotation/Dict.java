package com.flipped.mall.common.aspect.annotation;

import java.lang.annotation.*;

/**
 * 数据字典注解
 *
 * @author flipped
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dict {

    /**
     * 字典code
     */
    String value();

}