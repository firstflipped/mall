package com.flipped.mall.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义校验注解
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Documented
@Constraint(validatedBy = {PhoneValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface Phone {

    /**
     * 约束注解验证时的输出信息
     *
     * @return
     */
    String message() default "{com.laughingather.gulimall.valid.Phone.message}";

    /**
     * 约束注解验证时所属的组别
     *
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * 约束注解的有效负载
     *
     * @return
     */
    Class<? extends Payload>[] payload() default {};

}
