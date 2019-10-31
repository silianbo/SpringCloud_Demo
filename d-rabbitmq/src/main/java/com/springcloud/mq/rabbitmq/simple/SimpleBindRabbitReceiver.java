package com.springcloud.mq.rabbitmq.simple;

import com.springcloud.mq.constant.TopicRabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author bo
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = TopicRabbitConfig.QueueName.TOPIC_QUEUE, durable = "false"),
        exchange = @Exchange(value = TopicRabbitConfig.Exchange.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC),
        key = TopicRabbitConfig.Routingkey.TOPIC_ROUTINGKEY))
public class SimpleBindRabbitReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String msg) {
        logger.info("消费--->: [{}]", msg);
    }
}