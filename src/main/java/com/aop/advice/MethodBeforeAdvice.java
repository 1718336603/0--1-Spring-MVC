package com.aop.advice;

import java.lang.reflect.Method;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/2 12:23
 */
public interface MethodBeforeAdvice extends Advice{
    void before(Class clz, Method method, Object[] args)throws Throwable;
}
