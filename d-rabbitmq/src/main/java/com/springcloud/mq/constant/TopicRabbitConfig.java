package com.springcloud.mq.constant;

public class TopicRabbitConfig {
    /**
     * 消息队列名称
     */
    public interface QueueName {
        String TOPIC_QUEUE = "test.topic.hello";
        String QUEUE_NAME = "hello";
        String QUEUE_NAME_WORK = "hello_work";
    }

    /**
     * 交换器名称
     */
    public interface Exchange {
        String TOPIC_EXCHANGE = "test.topic.exchange";
    }

    /**
     * 绑定关系
     */
    public interface Routingkey {
        String TOPIC_ROUTINGKEY = "test.topic.routingKey";
    }
}
