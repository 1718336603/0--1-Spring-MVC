package com.mvc.render;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/4 14:38
 */

import com.mvc.RequestHandlerChain;

/**
 * 默认渲染 200
 */
public class DefaultRender implements Render {
    @Override
    public void render(RequestHandlerChain handlerChain) throws Exception {
        int status = handlerChain.getResponseStatus();
        handlerChain.getResponse().setStatus(status);
    }
}