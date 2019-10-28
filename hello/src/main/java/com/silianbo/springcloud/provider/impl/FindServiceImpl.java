package com.silianbo.springcloud.provider.impl;

import com.silianbo.springcloud.provider.FindService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author bo
 */
@Component
public class FindServiceImpl implements FindService {
    private static final Logger logger = LoggerFactory.getLogger(FindServiceImpl.class);

    @Override
    public String helloService(String username) {
        logger.info("fallback 调用");
        return "fallback 断熔" + username;
    }
}
