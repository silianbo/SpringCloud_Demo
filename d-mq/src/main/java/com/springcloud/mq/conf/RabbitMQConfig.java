package com.springcloud.mq.conf;

import com.springcloud.mq.constant.RabbitCons;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Queue对象参数：
 * <p>
 * Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
 * <p>
 * name --- 队列名称
 * durable --- 是否持久化，默认ture
 * exclusive 独有true会在使用此queue的消息订阅端断开后,自动将queue删除，默认false
 * autoDelete true宕机期间的消息则会丢失,false 重启之后重复发送,默认false
 * <p>
 * Rabbit 介绍
 * <p>
 * Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
 * Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
 * Queue:消息的载体,每个消息都会被投到一个或多个队列。
 * Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
 * Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
 * vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
 * Producer:消息生产者,就是投递消息的程序.
 * Consumer:消息消费者,就是接受消息的程序.
 * Channel:消息通道,在客户端的每个连接里,可建立多个channel.
 *
 * @author bo
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    Queue queueTest() {
        return new Queue(RabbitCons.QueueName.TOPIC_QUEUE, false);
    }

    @Bean
    TopicExchange exchangeTest() {
        return new TopicExchange(RabbitCons.Exchange.TOPIC_EXCHANGE);
    }

    /**
     * 方法名：bindingTest
     * 描述  ：
     * 通过key 进行直连
     *
     * @param queue:
     * @param exchange:
     * @return: org.springframework.amqp.core.Binding
     */
    @Bean
    Binding bindingTest(@Qualifier("queueTest") Queue queue, @Qualifier("exchangeTest") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitCons.Routingkey.TOPIC_ROUTINGKEY);
    }

    @Bean
    public Queue helloQueueSilianbo() {
        return new Queue(RabbitCons.QueueName.QUEUE_NAME_S);
    }

    @Bean
    Binding bindingHelloQueueSilianbo(@Qualifier("helloQueueSilianbo") Queue queue, @Qualifier("exchangeTest") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitCons.Routingkey.TOPIC_ROUTINGKEY_ALL);
    }

    @Bean
    public Queue helloQueue() {
        return new Queue(RabbitCons.QueueName.QUEUE_NAME);
    }

    @Bean
    public Queue helloWorkQueue() {
        return new Queue(RabbitCons.QueueName.QUEUE_NAME_WORK, false);
    }
}