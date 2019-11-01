package com.springcloud.mq.conf;

import com.springcloud.mq.constant.RedisCons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 需要针对单个客户端进行订阅 整体搬迁
 *
 * @author bo
 */
@Configuration
public class RedisConfig {
    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Autowired
    @Qualifier("redisReceiver")
    private MessageListener messageListener;

    @Bean
    MessageListenerAdapter listenerAdapter() {
        logger.info("new listener");
        return new MessageListenerAdapter(messageListener);
    }

    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        //客户端订阅 topic,可以多个
        container.addMessageListener(listenerAdapter, new ChannelTopic(RedisCons.CHANNEL));
        return container;
    }

}
