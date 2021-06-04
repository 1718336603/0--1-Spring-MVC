package com.aop;

import com.aop.advice.Advice;
import com.aop.advice.MethodAfterAdvice;
import com.aop.advice.MethodBeforeAdvice;
import com.aop.advice.ThrowsAdvice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/2 12:42
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Slf4j
public class ProxyAdvisor {

    /**
     * aop执行顺序
     */

    private int order;
    /**
     * 代理类
     */
    private Advice advice;

    /**
     * AspectJ表达式切点匹配器
     */
    private ProxyPointcut pointcut;



    public Object doProxy(AdviceChain adviceChain) throws Throwable {
        Object result=null;
        Method method=adviceChain.getMethod();
        Object[] args = adviceChain.getArgs();
        final Class<?> targetClass = adviceChain.getTargetClass();

        if(advice instanceof MethodAfterAdvice){
            ((MethodBeforeAdvice)advice).before(targetClass,method,args);
        }
        try {


            result = adviceChain.doAdviceChain(); //执行代理链方法
            //执行目标方法
//            result = proxy.invokeSuper(target,args);
            if(advice instanceof MethodAfterAdvice){
                ((MethodAfterAdvice)advice).after(targetClass,method,args);
            }
        }catch (Exception e){
            if(advice instanceof ThrowsAdvice){
                ((ThrowsAdvice) advice).afterThrowing(targetClass, method, args, e);
            }
            else {
                throw new Throwable(e);
            }
        }
        return result;
    }


}
