package com.silianbo.springcloud.deurekaserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 主要用于服务注册
 *
 * @author bo
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    private static final Logger logger = LoggerFactory.getLogger(EurekaServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
        logger.info("service-eureka-server启动完成！！！");
    }

}
