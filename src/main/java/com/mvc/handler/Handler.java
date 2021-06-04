package com.mvc.handler;

import com.mvc.RequestHandlerChain;

/**
 * @DESC 请求执行器 Handler
 * @AUTHOR zzf
 * @DATA 2021/6/4 13:18
 */

public interface Handler {

    /**
     * 请求的执行器
     */
    boolean handle(final RequestHandlerChain handlerChain) throws Exception;
}
