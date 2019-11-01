package com.springcloud.mq.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.springcloud.mq.constant.RabbitCons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author bo
 */
@Component
public class SimpleRabbitReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = RabbitCons.QueueName.QUEUE_NAME)
    public void process1(@Payload String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        channel.basicAck(deliveryTag, true);
        logger.info("[消费者1 RabbitMQ,队列{} ]msg={}", RabbitCons.QueueName.QUEUE_NAME, msg);
    }

    @RabbitListener(queues = RabbitCons.QueueName.QUEUE_NAME_WORK)
    public void process2(String msg) {
        logger.info("[消费者2 RabbitMQ,队列{} ]msg={}", RabbitCons.QueueName.QUEUE_NAME_WORK, msg);
    }

    @RabbitListener(queues = RabbitCons.QueueName.QUEUE_NAME_S)
    public void process3(String msg) {
        logger.info("[消费者2 RabbitMQ,队列{} ]msg={}", RabbitCons.QueueName.QUEUE_NAME_S, msg);
    }

    @RabbitListener(queues = RabbitCons.QueueName.TOPIC_QUEUE)
    public void process4(String msg) {
        logger.info("[消费者 RabbitMQ,队列{}] ]msg={}", RabbitCons.QueueName.TOPIC_QUEUE, msg);
    }
}