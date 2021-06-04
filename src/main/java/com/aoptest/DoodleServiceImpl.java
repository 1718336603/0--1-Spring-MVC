package com.aoptest;

import com.core.annotation.Service;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/2 9:18
 */

@Service
public class DoodleServiceImpl  implements DoodleService{

    @Override
    public String hello() {
        return "hello";
    }

    @Override
    public String helloForAspect() {
        return "helloForAspect";
    }


}
