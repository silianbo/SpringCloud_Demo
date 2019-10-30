package com.silianbo.springcloud.feign;

import com.silianbo.springcloud.feign.impl.FindServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 1. FeignClient value 为接入的服务名称
 * 2. 服务接口的接口地址
 * @author bo
 */
@FeignClient(value = "hello", fallback = FindServiceImpl.class)
public interface FindService {
    @GetMapping(value = "/s")
    String helloService(@RequestParam("username") String username);
}
