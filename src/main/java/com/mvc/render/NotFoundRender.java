package com.mvc.render;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/4 14:35
 */

import com.mvc.RequestHandlerChain;

import javax.servlet.http.HttpServletResponse;

/**
 * 渲染404
 */
public class NotFoundRender implements Render {
    @Override
    public void render(RequestHandlerChain handlerChain) throws Exception {
        handlerChain.getResponse().sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
