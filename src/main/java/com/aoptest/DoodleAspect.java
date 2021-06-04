package com.aoptest;

import com.aop.advice.AroundAdvice;
import com.aop.annotation.Aspect;
import com.aop.annotation.Order;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Order(1)
@Slf4j
@Aspect(pointcut = "execution(* com.aoptest.DoodleController.helloForAspect(..))")
public class DoodleAspect implements AroundAdvice {

    @Override
    public void after(Class clz, Method method, Object[] args) throws Throwable {
        log.info("-----------after  DoodleAspect-----------");
        log.info("class: {}, method: {}", clz, method.getName());
    }

    @Override
    public void before(Class clz, Method method, Object[] args) throws Throwable {
        log.info("-----------before  DoodleAspect-----------");
        log.info("class: {}, method: {}", clz.getName(), method.getName());
    }

    @Override
    public void afterThrowing(Class<?> clz, Method method, Object[] args, Throwable e) {
        log.error("-----------error  DoodleAspect-----------");
        log.error("class: {}, method: {}, exception: {}", clz, method.getName(), e.getMessage());
    }
}
