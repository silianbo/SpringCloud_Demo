package com.springcloud.mq.rabbitmq.simple;

import com.springcloud.mq.constant.TopicRabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author bo
 */
@Component
@RabbitListener(queues = TopicRabbitConfig.QueueName.QUEUE_NAME)
public class SimpleRabbitReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String msg) {
        logger.info("消费--->: [{}]", msg);
    }
}