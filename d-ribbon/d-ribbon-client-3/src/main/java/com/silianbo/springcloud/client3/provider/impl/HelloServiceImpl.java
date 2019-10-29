package com.silianbo.springcloud.client3.provider.impl;

import com.silianbo.springcloud.client3.provider.HelloService;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {
    @Override
    public String hiService() {
        return "异常了";
    }
}
