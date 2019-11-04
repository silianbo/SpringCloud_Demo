package com.springcloud.mq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.FanoutExchange;
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

    private final FanoutExchange fanoutExchange;

    public RabbitSender(RabbitTemplate rabbitTemplate, FanoutExchange fanoutExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.fanoutExchange = fanoutExchange;
    }

    public void sendFanout(String message) {
        String exchange = fanoutExchange.getName();
        this.rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
        logger.info("[生产者RabbitMQ 广播]exchange:{} routingKey:{} message:[{}]", exchange, "", message);
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
