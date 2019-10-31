package com.springcloud.mq.conf;

import com.springcloud.mq.constant.TopicRabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
 * Queue对象参数：
 * name --- 队列名称
 * durable --- 是否持久化，默认ture
 * exclusive 独有true会在使用此queue的消息订阅端断开后,自动将queue删除，默认false
 * autoDelete true宕机期间的消息则会丢失,false 重启之后重复发送,默认false
 * Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
 */
@Configuration
public class RabbitConfig {
    private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

    @Bean
    Queue queueTest() {
        return new Queue(TopicRabbitConfig.QueueName.TOPIC_QUEUE, false);
    }

    @Bean
    TopicExchange exchangeTest() {
        return new TopicExchange(TopicRabbitConfig.Exchange.TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingTest(@Qualifier("queueTest") Queue queue, @Qualifier("exchangeTest") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TopicRabbitConfig.Routingkey.TOPIC_ROUTINGKEY);
    }


    @Bean
    public Queue helloQueue() {
        return new Queue(TopicRabbitConfig.QueueName.QUEUE_NAME);
    }

    @Bean
    public Queue helloWorkQueue() {
        return new Queue(TopicRabbitConfig.QueueName.QUEUE_NAME_WORK, false);
    }
}