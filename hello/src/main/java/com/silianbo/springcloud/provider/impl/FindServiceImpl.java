package com.silianbo.springcloud.provider.impl;

import com.silianbo.springcloud.provider.FindService;
import org.springframework.stereotype.Component;


/**
 * @author bo
 */
@Component
public class FindServiceImpl implements FindService {
    @Override
    public String helloService(String username) {
        System.out.println("fallback 调用");
        return "fallback 断熔" + username;
    }
}
