package com.sample;

import com.core.annotation.Controller;
import com.mvc.annotation.RequestMapping;
import com.mvc.annotation.ResponseBody;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/4 12:38
 */
@Controller
@RequestMapping
public class DoodleController {


    @RequestMapping
    @ResponseBody
    public String hello() {
        return "hello doodle";
    }
}
