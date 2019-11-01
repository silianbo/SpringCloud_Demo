package com.springcloud.mq.task;

import com.springcloud.mq.constant.RabbitCons;
import com.springcloud.mq.constant.RedisCons;
import com.springcloud.mq.rabbitmq.RabbitSender;
import com.springcloud.mq.redis.RedisSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author bo
 */
@Component
@EnableScheduling
public class ScheduledTasks {
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private RedisSender redisSender;

    /**
     * 1s =(fixedRate = 10000)
     */
    @Scheduled(fixedRate = 10000)
    public void sendHelloMessage() {
        rabbitSender.send(RabbitCons.QueueName.QUEUE_NAME, RabbitCons.QueueName.QUEUE_NAME + " " + new Date());
    }


    @Scheduled(fixedRate = 10000)
    public void sendHelloWorkMessage() {
        rabbitSender.send(RabbitCons.QueueName.QUEUE_NAME_WORK, RabbitCons.QueueName.QUEUE_NAME_WORK + " " + new Date());
    }

    @Scheduled(fixedRate = 10000)
    public void sendBindMessage() {
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, RabbitCons.Routingkey.TOPIC_ROUTINGKEY, "Direct交换机的绑定 " + new Date());
    }

    @Scheduled(fixedRate = 10000)
    public void sendBoMessage() {
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, RabbitCons.Routingkey.TOPIC_ROUTINGKEY_ALL, "Topic交换机的绑定 " + new Date());
    }


    @Scheduled(fixedRate = 10000)
    public void sendMessageToRedis() {
        redisSender.send(RedisCons.CHANNEL, "Hello Redis!" + new Date());
    }
}