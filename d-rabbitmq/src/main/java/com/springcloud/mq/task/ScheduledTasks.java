package com.springcloud.mq.task;

import com.springcloud.mq.constant.TopicRabbitConfig;
import com.springcloud.mq.rabbitmq.RabbitSender;
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
    private final RabbitSender rabbitSender;

    public ScheduledTasks(RabbitSender rabbitSender) {
        this.rabbitSender = rabbitSender;
    }

    @Scheduled(fixedRate = 10000)
    public void sendHelloMessage() {
        rabbitSender.send(TopicRabbitConfig.QueueName.QUEUE_NAME, "QUEUE_NAME " + new Date());
    }


    @Scheduled(fixedRate = 10000)
    public void sendHelloWorkMessage() {
        rabbitSender.send(TopicRabbitConfig.QueueName.QUEUE_NAME_WORK, "QUEUE_NAME_WORK " + new Date());
    }

    @Scheduled(fixedRate = 10000)
    public void sendBindMessage() {
        rabbitSender.send(TopicRabbitConfig.Exchange.TOPIC_EXCHANGE, TopicRabbitConfig.Routingkey.TOPIC_ROUTINGKEY, "绑定队列 " + new Date());
    }
}