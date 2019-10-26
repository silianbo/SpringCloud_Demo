package com.silianbo.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HelloApplication {
    private static final Logger logger = LoggerFactory.getLogger(HelloApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
        logger.info("启动----HelloApplication-------------完成");
    }

}
