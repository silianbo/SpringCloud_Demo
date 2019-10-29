package com.silianbo.springcloud.client3.provider;


import com.silianbo.springcloud.client3.provider.impl.HelloServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "service-ribbon-client", fallback = HelloServiceImpl.class)
public interface HelloService {

    @GetMapping(value = "/s")
    String hiService();
}
