package com.silianbo.springcloud.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloApplication {
    private static final Logger logger = LoggerFactory.getLogger(HelloApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
        logger.info("启动----HelloApplication-------------完成");
    }

}
