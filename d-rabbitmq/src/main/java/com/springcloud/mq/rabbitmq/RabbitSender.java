package com.springcloud.mq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author bo
 */
@Component
public class RabbitSender {
    private static final Logger logger = LoggerFactory.getLogger(RabbitSender.class);
    private final AmqpTemplate rabbitTemplate;

    @Autowired
    public RabbitSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String queueName, String context) {
        logger.info("[RabbitSender] ---- queueName:{} context:[{}]", queueName, context);
        this.rabbitTemplate.convertAndSend(queueName, context);
    }

    public void send(String exchange, String routingKey, String context) {
        logger.info("[RabbitSender] ---- exchange:{} routingKey:{} context:[{}]", exchange, routingKey, context);
        this.rabbitTemplate.convertAndSend(exchange, routingKey, context);
    }
}
