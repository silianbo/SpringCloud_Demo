package com.springcloud.mq.task;

import com.springcloud.mq.constant.RabbitCons;
import com.springcloud.mq.rabbitmq.RabbitSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.springcloud.mq.constant.RabbitCons.Routingkey.*;

/**
 * @author bo
 */
@Component
@EnableScheduling
public class ScheduledTasks {
    private final RabbitSender rabbitSender;

    public ScheduledTasks(RabbitSender rabbitSender) {
        this.rabbitSender = rabbitSender;
    }

    /**
     * 1S
     */
    @Scheduled(fixedRate = 10000, zone = "Asia/Shanghai")
    public void sendFanoutMessage() {
        rabbitSender.sendFanout("广播 " + new Date());
    }

    /**
     * 10分钟一次
     */
    @Scheduled(cron = "0 0/10 * * * ?", zone = "Asia/Shanghai")
    public void sendDirectMessage() {
        rabbitSender.send(RabbitCons.Exchange.DIRECT_EXCHANGE, RabbitCons.QueueName.DIRECT_QUEUE_ORANGE, "直连 " + RabbitCons.QueueName.DIRECT_QUEUE_ORANGE + " " + new Date());
        rabbitSender.send(RabbitCons.Exchange.DIRECT_EXCHANGE, RabbitCons.QueueName.DIRECT_QUEUE_BLACK_1, "直连 " + RabbitCons.QueueName.DIRECT_QUEUE_BLACK_1 + " " + new Date());
        rabbitSender.send(RabbitCons.Exchange.DIRECT_EXCHANGE, RabbitCons.QueueName.DIRECT_QUEUE_BLACK_2, "直连 " + RabbitCons.QueueName.DIRECT_QUEUE_BLACK_2 + " " + new Date());
        rabbitSender.send(RabbitCons.Exchange.DIRECT_EXCHANGE, RabbitCons.QueueName.DIRECT_QUEUE_GREEN, "直连 " + RabbitCons.QueueName.DIRECT_QUEUE_GREEN + " " + new Date());
    }

    /**
     * 3分钟一次
     */
    @Scheduled(cron = "0 0/3 * * * ?", zone = "Asia/Shanghai")
    public void sendTopicMessage() {
        /*
         * 发送订阅 适配 *.orange.*
         */
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, "send.orange.name1", "订阅 " + TOPIC_ROUTINGKEY_ORANGE + new Date());
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, "send.orange.name2", "订阅 " + TOPIC_ROUTINGKEY_ORANGE + new Date());
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, "send1.orange.name", "订阅 " + TOPIC_ROUTINGKEY_ORANGE + new Date());
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, "send2.orange.name", "订阅 " + TOPIC_ROUTINGKEY_ORANGE + new Date());

        /*
         * 发送订阅 适配 *.*.black
         */
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, "send.silianbo.black", "订阅 " + TOPIC_ROUTINGKEY_BLACK + new Date());
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, "send1.silianbo.black", "订阅 " + TOPIC_ROUTINGKEY_BLACK + new Date());
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, "send.silianbo1.black", "订阅 " + TOPIC_ROUTINGKEY_BLACK + new Date());

        /*
         * 发送订阅 适配 green.#
         */
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, "green.name1", "订阅 " + TOPIC_ROUTINGKEY_GREEN + new Date());
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, "green.name2", "订阅 " + TOPIC_ROUTINGKEY_GREEN + new Date());
        rabbitSender.send(RabbitCons.Exchange.TOPIC_EXCHANGE, "green.name3", "订阅 " + TOPIC_ROUTINGKEY_GREEN + new Date());
    }
}