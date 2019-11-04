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
 * 交换器---key -- 队列
 * 直连方式
 * <p>
 * ------------------------orange  ->test.direct.orange
 * test.direct.exchange--->black   ->test.direct.black.1,test.direct.black.2
 * ------------------------green   ->test.direct.green
 *
 * @author bo
 */
@Configuration
public class RabbitMqDirectConfig {
    @Bean
    public Queue directQueueOrange() {
        return new Queue(RabbitCons.QueueName.DIRECT_QUEUE_ORANGE, false);
    }

    @Bean
    public Queue directQueueBlack1() {
        return new Queue(RabbitCons.QueueName.DIRECT_QUEUE_BLACK_1, false);
    }

    @Bean
    public Queue directQueueBlack2() {
        return new Queue(RabbitCons.QueueName.DIRECT_QUEUE_BLACK_2, false);
    }

    @Bean
    public Queue directQueueGreen() {
        return new Queue(RabbitCons.QueueName.DIRECT_QUEUE_GREEN, false);
    }

    @Bean
    TopicExchange directExchange() {
        return new TopicExchange(RabbitCons.Exchange.DIRECT_EXCHANGE);
    }

    @Bean
    Binding bindingDirectQueueOrange(@Qualifier("directQueueOrange") Queue queue, @Qualifier("directExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitCons.Routingkey.DIRECT_ROUTINGKEY_ORANGE);
    }

    @Bean
    Binding bindingDirectQueueBlack1(@Qualifier("directQueueBlack1") Queue queue, @Qualifier("directExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitCons.Routingkey.DIRECT_ROUTINGKEY_BLACK);
    }

    @Bean
    Binding bindingDirectQueueBlack2(@Qualifier("directQueueOrange") Queue queue, @Qualifier("directExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitCons.Routingkey.DIRECT_ROUTINGKEY_BLACK);
    }

    @Bean
    Binding bindingDirectQueueGreen(@Qualifier("directQueueOrange") Queue queue, @Qualifier("directExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitCons.Routingkey.DIRECT_ROUTINGKEY_GREEN);
    }
}