package com.mvc;

import com.mvc.Bean.ControllerInfo;
import com.mvc.handler.Handler;
import com.mvc.handler.JspHandler;
import com.mvc.handler.PreRequestHandler;
import com.mvc.handler.SimpleUrlHandler;
import lombok.extern.slf4j.Slf4j;
import com.mvc.handler.ControllerHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/4 9:31
 */

@Slf4j
public class DispatcherServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        HANDLER.add(new PreRequestHandler());
        HANDLER.add(new SimpleUrlHandler(getServletContext()));
        HANDLER.add(new JspHandler(getServletContext()));
        HANDLER.add(new ControllerHandler());
    }

    /**
     * 请求执行链
     */
    private final List<Handler> HANDLER = new ArrayList<>();

    /**
     * 执行请求
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestHandlerChain handlerChain = new RequestHandlerChain(HANDLER.iterator(), req, resp);
        handlerChain.doHandlerChain();
        handlerChain.doRender();
    }

//    /**
//     * 控制器处理器加载类并加载路径与控制器的映射
//     */
//    private ControllerHandler controllerHandler = new ControllerHandler();
//
//    /**
//     * 结果处理器找到路径对应的控制器并执行返回结果如果有ResponseBody的注解则直接返回否则就重定向路径
//     */
//    private ResultRender resultRender = new ResultRender();
//
//
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // 设置请求编码方式
//        req.setCharacterEncoding("UTF-8");
//        //获取请求方法和请求路径
//        String requestMethod = req.getMethod();
//        String requestPath = req.getPathInfo();
//        log.info("[DoodleConfig] {} {}", requestMethod, requestPath);
//        if (requestPath.endsWith("/")) {
//            requestPath = requestPath.substring(0, requestPath.length() - 1);
//        }
//
//        //找到对应控制器
//        ControllerInfo controllerInfo = controllerHandler.getController(requestMethod, requestPath);
//        log.info("{}", controllerInfo);
//        if (null == controllerInfo) {
//            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//        resultRender.invokeController(req, resp, controllerInfo);
//    }

}
