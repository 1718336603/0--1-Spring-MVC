package com.aoptest;

import com.core.BeanContainer;
import com.aop.Aop;
import com.ioc.Ioc;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/2 9:26
 */
@Slf4j
public class IocTest {

    @Test
    public void doIocTest(){
        BeanContainer beanContainer=BeanContainer.getInstance();
        beanContainer.loadBeanContainer("com.aoptest");
        new Aop().doAop();
        new Ioc().doIoc();
        DoodleController doodleController= (DoodleController) beanContainer.getBean(DoodleController.class);
        doodleController.hello();
        doodleController.helloForAspect();
    }
}
