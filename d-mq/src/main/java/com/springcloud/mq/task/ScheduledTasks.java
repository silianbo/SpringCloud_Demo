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
     * 1S
     */
    @Scheduled(fixedRate = 10000, zone = "Asia/Shanghai")
    public void sendHelloMessage() {
        rabbitSender.send(RabbitCons.QueueName.QUEUE_NAME, RabbitCons.QueueName.QUEUE_NAME + " " + new Date());
    }

    /**
     * 3分钟一次
     */
    @Scheduled(cron = "0 0/3 * * * ?", zone = "Asia/Shanghai")
    public void sendHelloWorkMessage() {
        rabbitSender.send(RabbitCons.QueueName.QUEUE_NAME_WORK, RabbitCons.QueueName.QUEUE_NAME_WORK + " " + new Date());
    }

    /**
     * 10分钟一次
     */
    @Scheduled(cron = "0 0/10 * * * ?", zone = "Asia/Shanghai")
    public void sendBindMessage() {
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, RabbitCons.Routingkey.TOPIC_ROUTINGKEY, "Direct交换机的绑定 " + new Date());
    }

    /**
     * 15分钟一次
     */
    @Scheduled(cron = "0 0/15 * * * ?", zone = "Asia/Shanghai")
    public void sendBoMessage() {
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, RabbitCons.Routingkey.TOPIC_ROUTINGKEY_ALL, "Topic交换机的绑定 " + new Date());
    }

    /**
     * 周一到周五 9点
     */
    @Scheduled(cron = "0 0 9 ? * MON-FRI", zone = "Asia/Shanghai")
    public void sendMessageToRedis() {
        redisSender.send(RedisCons.CHANNEL, "Hello Redis!" + new Date());
    }
}