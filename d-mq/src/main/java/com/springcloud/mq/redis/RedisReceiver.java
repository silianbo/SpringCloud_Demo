package com.springcloud.mq.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @author bo
 */
@Service("redisReceiver")
public class RedisReceiver implements MessageListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String body = new String(message.getBody());
        String channel = new String(message.getChannel());
        logger.info("[消费者Redis ]body is:[{}],channel is[{}].", body, channel);
    }
}