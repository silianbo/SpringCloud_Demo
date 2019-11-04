package com.springcloud.mq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author bo
 */
@Component
public class RabbitSender implements RabbitTemplate.ConfirmCallback {
    private static final Logger logger = LoggerFactory.getLogger(RabbitSender.class);
    private final RabbitTemplate rabbitTemplate;

    public RabbitSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String exchange, String routingKey, String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("callbackSender UUID: " + correlationData.getId());
        rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
        logger.info("[RabbitMQ sender]; exchange:{} routingKey:{} message:[{}]", exchange, routingKey, message);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("correlationData id:" + correlationData.getId());
        if (ack) {
            logger.info("CallBackConfirm receive success！");
        } else {
            logger.info("CallBackConfirm receive fail！");
        }

        if (cause != null) {
            logger.info("CallBackConfirm Cause: " + cause);
        }
    }
}
