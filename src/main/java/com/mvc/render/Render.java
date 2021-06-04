package com.mvc.render;

import com.mvc.RequestHandlerChain;

/**
 * @DESC 渲染请求结果 interface
 * @AUTHOR zzf
 * @DATA 2021/6/4 13:22
 */
public interface Render {

    /**
     * 执行渲染
     */
    void render(RequestHandlerChain handlerChain) throws Exception;
}
