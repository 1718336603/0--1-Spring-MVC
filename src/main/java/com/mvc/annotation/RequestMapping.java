package com.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/3 14:01
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    /**
     * 请求路径
     * @return
     */
    String value() default "";

    /**
     * 请求方法
     * @return
     */
    RequestMethod method() default RequestMethod.GET;
}