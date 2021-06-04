package com;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/4 12:21
 */

import com.aop.Aop;
import com.ioc.Ioc;
import com.core.BeanContainer;
import com.mvc.Server.Server;
import com.mvc.Server.TomcatServer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Doodle Starter
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class Doodle {

    /**
     * 全局配置
     */
    @Getter
    private static Configuration configuration = Configuration.builder().build();

    /**
     * 默认服务器
     */
    @Getter
    private static Server server;

    /**
     * 启动
     */
    public static void run(Class<?> bootClass) {
        run(Configuration.builder().bootClass(bootClass).build());
    }

    /**
     * 启动
     */
    public static void run(Class<?> bootClass, int port) {
        run(Configuration.builder().bootClass(bootClass).serverPort(port).build());
    }

    /**
     * 启动
     */
    public static void run(Configuration configuration) {
//        log.info(configuration.toString());
        new Doodle().start(configuration);
    }

    /**
     * 初始化
     */
    private void start(Configuration configuration) {
        try {
            Doodle.configuration = configuration;
            String basePackage = configuration.getBootClass().getPackage().getName();
            BeanContainer.getInstance().loadBeanContainer(basePackage);
            new Aop().doAop();
            new Ioc().doIoc();

            server = new TomcatServer(configuration);
            server.startServer();
        } catch (Exception e) {
            log.error("Doodle 启动失败", e);
        }
    }
}