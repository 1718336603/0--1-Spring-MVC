package com.aop.annotation;

import java.lang.annotation.*;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/2 12:02
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 切点表达式
     */
    String pointcut() default "";

}
