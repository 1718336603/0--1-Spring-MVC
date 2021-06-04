package com.core;

import com.core.annotation.Component;
import com.core.annotation.Controller;
import com.core.annotation.Repository;
import com.core.annotation.Service;
import com.aop.annotation.Aspect;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.utils.*;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/5/30 23:08
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    /**
     * 用于存储bean
     */
    private final HashMap<Class<?>,Object> beanMap=new HashMap<>();

//
    /**
     * 与原来的不一样，他用menu 就简单的用了构造器私有 menu应该跟安全
     */
    private static volatile BeanContainer beanContainer=new BeanContainer();

    /**
     * 这里面返回单例的容器
     */
    public static BeanContainer getInstance() {
        return beanContainer;
    }


    /**
     * 通过class获取类
     */
    public Object getBean(Class<?> clz){
        if(null==clz){
            return null;
        }
        return beanMap.get(clz);
    }

    /**
     * 获取说有bean的集合
     */
    public Set<Object> getBeans() {
        return new HashSet<>(beanMap.values());
    }

    /**
     * 添加一个Bean实例
     */
    public Object addBean(Class<?> clz, Object bean) {
        return beanMap.put(clz, bean);
    }

    /**
     * 移除一个Bean实例
     */
    public void removeBean(Class<?> clz) {
        beanMap.remove(clz);
    }

    /**
     * Bean实例数量
     */
    public int size() {
        return beanMap.size();
    }

    /**
     * 所有Bean的Class集合
     */
    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    /**
     * 通过注解获取Bean的Class集合
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        return beanMap.keySet()
                .stream()
                .filter(clz -> clz.isAnnotationPresent(annotation))
                .collect(Collectors.toSet());
    }

    /**
     * 通过父类获取子类Bean的Class集合
     */
    public Set<Class<?>> getClassesBySuper(Class<?> superClass) {
        return beanMap.keySet()
                .stream()
                .filter(superClass::isAssignableFrom)
                .filter(clz -> !clz.equals(superClass))
                .collect(Collectors.toSet());
    }


    private volatile boolean isLoadBean = false;
    /**
     * 需要被加载的类的列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Controller.class, Service.class, Repository.class, Aspect.class);


    public void loadBeanContainer(String basePackage){
        if(isLoadBean){
            log.warn("bean已经加载");
            return;
        }

        Set<Class<?>> classSet = ClassUtil.getPackageClass(basePackage);

        classSet.stream()
                .filter(clz -> {
                    for(Class<? extends Annotation> annotation : BEAN_ANNOTATION){
                        if(clz.isAnnotationPresent(annotation)){
                            return true;
                        }
                    }
                    return false;
                }).forEach(clz ->{
            addBean(clz,ClassUtil.newInstance(clz));
//            System.out.println(beanMap.get(clz).toString());
        });
        System.out.println(beanMap.size());
        isLoadBean=true;
    }
}
