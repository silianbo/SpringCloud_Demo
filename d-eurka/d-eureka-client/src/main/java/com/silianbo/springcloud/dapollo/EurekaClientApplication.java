package com.silianbo.springcloud.dapollo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author bo
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientApplication {
    private static final Logger logger = LoggerFactory.getLogger(EurekaClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
        logger.info("d-eureka-client启动完成！！！");
    }

}
