package com.aop.advice;

import java.lang.reflect.Method;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/2 12:24
 */
public interface ThrowsAdvice extends Advice{
    void afterThrowing(Class<?> clz, Method method, Object[] args, Throwable e);
}
