package com.silianbo.springcloud.dribbon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 主要用于客户端负载均衡
 *
 * @author bo
 */
@SpringBootApplication
@EnableEurekaClient
public class RibbonApplication {
    private static final Logger logger = LoggerFactory.getLogger(RibbonApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
        logger.info("d-ribbon启动完成！！！");
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
