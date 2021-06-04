package com.test;

import com.aop.advice.AroundAdvice;
import com.aop.annotation.Aspect;
import com.aop.annotation.Order;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/3 11:11
 */
@Order(2)
@Slf4j
//@Aspect(pointcut = "@within(com.core.annotation.Controller)")
public class DoodleAspect2 implements AroundAdvice {

    @Override
    public void after(Class clz, Method method, Object[] args) throws Throwable {
        log.info("-----------after  DoodleAspect2-----------");
        log.info("class: {}, method: {}", clz, method.getName());
    }

    @Override
    public void before(Class clz, Method method, Object[] args) throws Throwable {
        log.info("-----------before  DoodleAspect2-----------");
        log.info("class: {}, method: {}", clz.getName(), method.getName());
    }

    @Override
    public void afterThrowing(Class<?> clz, Method method, Object[] args, Throwable e) {
        log.error("-----------error  DoodleAspect2-----------");
        log.error("class: {}, method: {}, exception: {}", clz, method.getName(), e.getMessage());
    }
}
