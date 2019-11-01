package com.springcloud.mq.rabbitmq.simple;

import com.springcloud.mq.constant.RabbitCons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author bo
 */
@Component
public class SimpleRabbitReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = RabbitCons.QueueName.QUEUE_NAME)
    public void process1(String msg) {
        logger.info("[消费者1 RabbitMQ,队列[{} ]msg={}", RabbitCons.QueueName.QUEUE_NAME, msg);
    }

    @RabbitListener(queues = RabbitCons.QueueName.QUEUE_NAME_WORK)
    public void process2(String msg) {
        logger.info("[消费者2 RabbitMQ,队列[{} ]msg={}", RabbitCons.QueueName.QUEUE_NAME_WORK, msg);
    }

    @RabbitListener(queues = RabbitCons.QueueName.QUEUE_NAME_S)
    public void process3(String msg) {
        logger.info("[消费者2 RabbitMQ,队列[{} ]msg={}", RabbitCons.QueueName.QUEUE_NAME_S, msg);
    }

    @RabbitListener(queues = RabbitCons.QueueName.TOPIC_QUEUE)
    public void process4(String msg) {
        logger.info("[消费者 RabbitMQ,队列[{}] ]msg={}", RabbitCons.QueueName.TOPIC_QUEUE, msg);
    }
}