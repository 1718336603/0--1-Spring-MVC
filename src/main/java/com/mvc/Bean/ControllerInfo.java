package com.mvc.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @DESC 用于存储controller的一些基本信息
 * @AUTHOR zzf
 * @DATA 2021/6/3 14:40
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerInfo {

    /**
     * controller的类
     */
    private Class<?> controllerClass;

    /**
     * 执行的方法
     */
    private Method invokeMethod;


    /**
     * 方法名对应的参数类型
     */
    private Map<String,Class<?>> methodParameter;
}
