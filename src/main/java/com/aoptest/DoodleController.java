package com.aoptest;

import com.core.annotation.Controller;
import com.ioc.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/2 9:15
 */
@Controller
@Slf4j
public class DoodleController {

    @Autowired
    private DoodleService doodleService;

    public void hello(){
//        System.out.println(doodleService.hello());
        log.info(doodleService.hello());
    }

    public void helloForAspect(){
//        System.out.println(doodleService.hello());
        log.info(doodleService.helloForAspect());
    }
}
