package com.springcloud.mq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author bo
 */
@Component
public class RabbitSender implements RabbitTemplate.ConfirmCallback {
    private static final Logger logger = LoggerFactory.getLogger(RabbitSender.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String queueName, String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("callbackSender UUID: " + correlationData.getId());
        rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.convertAndSend(queueName, (Object) message, correlationData);
        logger.info("[生产者RabbitMQ ]queueName:{} message:[{}]", queueName, message);
    }

    public void send(String exchange, String routingKey, String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("callbackSender UUID: " + correlationData.getId());
        rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
        logger.info("[生产者RabbitMQ ]exchange:{} routingKey:{} message:[{}]", exchange, routingKey, message);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("消息id:" + correlationData.getId());
        if (ack) {
            logger.info("CallBackConfirm 消息消费成功！");
        } else {
            logger.info("CallBackConfirm 消息消费失败！");
        }

        if (cause != null) {
            logger.info("CallBackConfirm Cause: " + cause);
        }
    }
}
