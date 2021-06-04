package com.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/3 14:23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {

    /**
     * 方法参数别名
     * @return
     */
    String value() default "";

    /**
     * 是否必填
     * @return
     */

    boolean required() default true;
}
