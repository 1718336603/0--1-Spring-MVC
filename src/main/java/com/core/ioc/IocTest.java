package com.core.ioc;

import com.core.BeanContainer;
import com.core.test.DoodleController;
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
        beanContainer.loadBeanContainer("com.core");
        new Ioc().doIoc();
        DoodleController doodleController= (DoodleController) beanContainer.getBean(DoodleController.class);
        doodleController.hello();
    }
}
