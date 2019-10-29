package com.silianbo.springcloud.dhystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author bo
 */
@SpringBootApplication
public class HystrixApplication {
    private static final Logger logger = LoggerFactory.getLogger(HystrixApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
        logger.info("service-hystrix启动完成！！！");
    }

}
