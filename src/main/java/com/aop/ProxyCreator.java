package com.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @DESC Enhancer是cglib中使用频率很高的一个类，它是一个字节码增强器，可以用来为无接口的类创建代理。
 *       它的功能与java自带的Proxy类挺相似的。它会根据某个给定的类创建子类，并且所有非final的方法都带有回调钩子。
 * @AUTHOR zzf
 * @DATA 2021/6/2 13:43
 */
public final class ProxyCreator {



    // 调用Enhancer生成代理类
    public static Object createProxy(Class<?> targetClass, List<ProxyAdvisor> proxyList) {

        return Enhancer.create(targetClass, new AdviceMethodInterceptor(targetClass,proxyList));

        //旧版不支持多次代理
//        return Enhancer.create(targetClass,
//                (MethodInterceptor) (target, method, args, proxy) ->
//                        proxyAdvisor.doProxy(target, targetClass, method, args, proxy));
    }


    /**
     * cglib MethodInterceptor实现类
     */
    private static class AdviceMethodInterceptor implements MethodInterceptor {
        /**
         * 目标类
         */
        private final Class<?> targetClass;

        /**
         * 代理通知列表
         */
        private List<ProxyAdvisor> proxyList;

        public AdviceMethodInterceptor(Class<?> targetClass, List<ProxyAdvisor> proxyList) {
            this.targetClass = targetClass;
            this.proxyList = proxyList;
        }


        @Override
        public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            return new AdviceChain(targetClass, target, method, args, proxy, proxyList).doAdviceChain();
        }
    }
}