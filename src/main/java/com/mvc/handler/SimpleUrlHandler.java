package com.mvc.handler;

import com.Doodle;
import com.mvc.RequestHandlerChain;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/4 14:02
 */

@Slf4j
public class SimpleUrlHandler implements Handler{
    /**
     * tomcat默认RequestDispatcher的名称
     * TODO: 其他服务器默认的RequestDispatcher.如WebLogic为FileServlet
     */
    private static final String TOMCAT_DEFAULT_SERVLET = "default";

    /**
     * 默认的RequestDispatcher,处理静态资源
     */
    private RequestDispatcher defaultServlet;

    public SimpleUrlHandler(ServletContext servletContext) {
        defaultServlet = servletContext.getNamedDispatcher(TOMCAT_DEFAULT_SERVLET);

        if (null == defaultServlet) {
            throw new RuntimeException("没有默认的Servlet");
        }

        log.info("The default servlet for serving static resource is [{}]", TOMCAT_DEFAULT_SERVLET);
    }


    @Override
    public boolean handle(final RequestHandlerChain handlerChain) throws Exception {
        if (isStaticResource(handlerChain.getRequestPath())) {
            defaultServlet.forward(handlerChain.getRequest(), handlerChain.getResponse());
            return false;
        }
        return true;
    }

    /**
     * 是否为静态资源
     */
    private boolean isStaticResource(String url) {
        return url.startsWith(Doodle.getConfiguration().getAssetPath());
    }
}
