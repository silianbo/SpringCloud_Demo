package com.springcloud.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.springcloud.mq.constant.RabbitCons.QueueName.*;

/**
 * @author bo
 */
@Component
public class RabbitReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void process(@Payload String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        channel.basicAck(deliveryTag, false);
        logger.info("[消费者(广播方式)  RabbitMQ]msg={}", msg);
    }

    @RabbitListener(queues = DIRECT_QUEUE_BLACK_1)
    public void process1(@Payload String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        channel.basicAck(deliveryTag, false);
        logger.info("[消费者1 RabbitMQ,队列{} ]msg={}", DIRECT_QUEUE_BLACK_1, msg);
    }

    @RabbitListener(queues = DIRECT_QUEUE_BLACK_2)
    public void process2(String msg) {
        logger.info("[消费者2 RabbitMQ,队列{} ]msg={}", DIRECT_QUEUE_BLACK_2, msg);
    }

    @RabbitListener(queues = DIRECT_QUEUE_GREEN)
    public void process3(String msg) {
        logger.info("[消费者2 RabbitMQ,队列{} ]msg={}", DIRECT_QUEUE_GREEN, msg);
    }

    @RabbitListener(queues = DIRECT_QUEUE_ORANGE)
    public void process4(String msg) {
        logger.info("[消费者4 RabbitMQ,队列{}] ]msg={}", DIRECT_QUEUE_ORANGE, msg);
    }

    @RabbitListener(queues = TOPIC_QUEUE_ORANGE)
    public void process5(String msg) {
        logger.info("[消费者5 RabbitMQ,队列{}] ]msg={}", TOPIC_QUEUE_ORANGE, msg);
    }

    @RabbitListener(queues = TOPIC_QUEUE_BLACK)
    public void process6(String msg) {
        logger.info("[消费者6 RabbitMQ,队列{}] ]msg={}", TOPIC_QUEUE_BLACK, msg);
    }

    @RabbitListener(queues = TOPIC_QUEUE_GREEN)
    public void process7(String msg) {
        logger.info("[消费者7 RabbitMQ,队列{}] ]msg={}", TOPIC_QUEUE_GREEN, msg);
    }
}