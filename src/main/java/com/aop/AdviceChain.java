package com.aop;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/2 17:40
 */
@Slf4j
public class AdviceChain {
    /**
     * 目标类
     */
    @Getter
    private final Class<?> targetClass;
    /**
     * 目标实例
     */
    @Getter
    private final Object target;
    /**
     * 目标方法
     */
    @Getter
    private final Method method;
    /**
     * 目标方法参数
     */
    @Getter
    private final Object[] args;
    /**
     * 代理方法
     */
    private final MethodProxy methodProxy;
    /**
     * 代理通知列
     */
    private List<ProxyAdvisor> proxyList;
    /**
     * 代理通知列index
     */
    private int adviceIndex = 0;


    public AdviceChain(Class<?> targetClass, Object target, Method method, Object[] args, MethodProxy methodProxy, List<ProxyAdvisor> proxyList) {
        this.targetClass = targetClass;
        this.target = target;
        this.method = method;
        this.args = args;
        this.methodProxy = methodProxy;
        this.proxyList = proxyList;
    }


    /**
     * 递归执行 执行代理通知列
     */
    public Object doAdviceChain() throws Throwable {

        Object result;
        //找到第一个匹配的代理类
        while(adviceIndex<proxyList.size()
                &&!proxyList.get(adviceIndex).getPointcut().matches(method)){
            adviceIndex++;
        }

        if(adviceIndex<proxyList.size()){
            result = proxyList.get(adviceIndex++).doProxy(this);
        }
        else {
            result = methodProxy.invokeSuper(target,args);
        }
        return result;
    }
}
