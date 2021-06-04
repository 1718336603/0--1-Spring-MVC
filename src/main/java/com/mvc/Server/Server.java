package com.mvc.Server;

/**
 * @DESC 服务器 interface
 * @AUTHOR zzf
 * @DATA 2021/6/4 10:08
 */
public interface Server {
    /**
     * 启动服务器
     */
    void startServer() throws Exception;

    /**
     * 停止服务器
     */
    void stopServer() throws Exception;
}