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

    @RabbitListener(queues = {FANOUT_QUEUENAME})
    public void processFanout(@Payload String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        channel.basicAck(deliveryTag, false);
        logger.info("[RabbitMQ receiver use mode fanout] msg=[{}]", msg);
    }

    @RabbitListener(queues = {DIRECT_QUEUE_ORANGE, DIRECT_QUEUE_BLACK_1, DIRECT_QUEUE_BLACK_2, DIRECT_QUEUE_GREEN})
    public void processDirect(@Payload String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        channel.basicAck(deliveryTag, false);
        logger.info("[RabbitMQ receiver use mode direct]msg=[{}]", msg);
    }

    @RabbitListener(queues = {TOPIC_QUEUE_ORANGE, TOPIC_QUEUE_BLACK, TOPIC_QUEUE_GREEN})
    public void processTopic(@Payload String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        channel.basicAck(deliveryTag, false);
        logger.info("[RabbitMQ receiver use mode topic]msg=[{}]", msg);
    }
}