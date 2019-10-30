package com.silianbo.springcloud.ribbon.controller;

import com.silianbo.springcloud.feign.entity.User;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon工作时分为两步：
 * 第一步先选择 Eureka Server, 它优先选择在同一个Zone且负载较少的Server；
 * <p>
 * 第二步再根据用户指定的策略，在从Server取到的服务注册列表中选择一个地址。
 * 其中Ribbon提供了多种策略，例如轮询、随机、根据响应时间加权等。
 * <p>
 *
 * @author bo
 */
@RestController
public class RibbonHiController {
    private static final Logger logger = LoggerFactory.getLogger(RibbonHiController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * http://localhost:8888/ribbon/user/1111   ---  调用内部接口
     *
     * @param id
     * @return
     */
    @GetMapping("/ribbon/user/{id}")
    public User find(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setName(RandomStringUtils.randomAlphanumeric(10));
        return user;
    }

    /**
     * http://localhost:8888/hello/user/1111   ---  调用外部接口
     *
     * @param id
     * @return
     */
    @GetMapping("/hello/user/{id}")
    public User findById(@PathVariable Long id) {
        return this.restTemplate.getForObject("http://hello/ribbon/user/" + id, User.class);
    }

    /**
     * http://localhost:8888/log-user-instance    ---  通过hello 的服务信息
     */
    @GetMapping("/log-user-instance")
    public void logUserInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("hello");
        // 打印当前选择的是哪个节点
        logger.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }
}
