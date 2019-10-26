package com.silianbo.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class FindServiceImpl implements FindService {
    @Override
    public String helloService(String username) {
        System.out.println("fallback 调用");
        return "fallback 断熔" + username;
    }
}
