package com.springcloud.mq.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author bo
 */
@Component
public class RedisSender {
    private static final Logger logger = LoggerFactory.getLogger(RedisSender.class);
    private final StringRedisTemplate stringRedisTemplate;

    public RedisSender(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void send(String channel, String message) {
        stringRedisTemplate.convertAndSend(channel, message);
        logger.info("[生产者Redis ]channel:{} message:[{}]", channel, message);
    }
}