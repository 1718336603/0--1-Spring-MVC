package com.aop;

import com.core.BeanContainer;
import com.aop.advice.Advice;
import com.aop.annotation.Aspect;
import com.aop.annotation.Order;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/2 13:09
 */

@Slf4j
public class Aop {
    private BeanContainer beanContainer;

    public Aop(){
        beanContainer=BeanContainer.getInstance();
    }

    public void doAop(){

        //创建所有的代理通实例列表
         List<ProxyAdvisor> proxyList = beanContainer.getClassesBySuper(Advice.class)
                .stream()
                .filter(clz -> clz.isAnnotationPresent(Aspect.class))
                .map(this::createProxyAdvisor)
                .collect(Collectors.toList());
//        System.out.println(proxyList.toString());
        beanContainer
                .getClasses()
                .stream()
                .filter(clz -> !Advice.class.isAssignableFrom(clz))
                .filter(clz -> !clz.getClass().isAnnotationPresent(Aspect.class))
                .forEach(clz -> {
//                    System.out.println(clz);
                    List<ProxyAdvisor> matchProxies=createMatchProxies(proxyList,clz);
                    if(matchProxies.size()>0){
                        Object proxyBean = ProxyCreator.createProxy(clz, matchProxies);
                        beanContainer.addBean(clz,proxyBean);
                    }
                });

    }


    /**
     * 通过Aspect切面类创建代理通知类
     */
    private ProxyAdvisor createProxyAdvisor(Class<?> aspectClass) {
        int order=0;
        if(aspectClass.isAnnotationPresent(Order.class)){
            order = aspectClass.getAnnotation(Order.class).value();
        }
        String expression = aspectClass.getAnnotation(Aspect.class).pointcut();
        ProxyPointcut proxyPointcut = new ProxyPointcut();
        proxyPointcut.setExpression(expression);
        Advice advice = (Advice) beanContainer.getBean(aspectClass);
        return new ProxyAdvisor(order, advice, proxyPointcut);
    }


    private List<ProxyAdvisor> createMatchProxies(List<ProxyAdvisor> proxyList, Class<?> targetClass) {

//        System.out.println(proxyList.toString());
        Object targetBean = beanContainer.getBean(targetClass);
        return proxyList
                .stream()
                .filter(advisor -> advisor.getPointcut().matches(targetBean.getClass()))
                .sorted(Comparator.comparingInt(ProxyAdvisor::getOrder))
                .collect(Collectors.toList());
    }
}
