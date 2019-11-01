package com.springcloud.mq.rabbitmq.simple;

import com.springcloud.mq.constant.RabbitCons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 两种监听方式都行
 *
 * @author bo
 */
@Component
//@RabbitListener(bindings = @QueueBinding(value = @Queue(value = TopicRabbitConfig.QueueName.TOPIC_QUEUE, durable = "false"),
//        exchange = @Exchange(value = TopicRabbitConfig.Exchange.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC),
//        key = TopicRabbitConfig.Routingkey.TOPIC_ROUTINGKEY))
@RabbitListener(queues = RabbitCons.QueueName.TOPIC_QUEUE)
public class SimpleBindRabbitReceiver {
    private static final Logger logger = LoggerFactory.getLogger(SimpleBindRabbitReceiver.class);

    @RabbitHandler
    public void process(String msg) {
        logger.info("[消费者RabbitMQ ]msg={}", msg);
    }
}