package com.springcloud.mq.conf;

import com.springcloud.mq.constant.RabbitCons;
import org.springframework.amqp.core.*;
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
 * 广播方式
 * <p>
 *
 * @author bo
 */
@Configuration
public class RabbitMqFanoutConfig {
    /**
     * 方法名：autoDeleteQueue
     * 描述  ：
     * AnonymousQueue类型的队列，它的名字是由客户端生成的，而且是非持久的，独占的，自动删除的队列
     *
     * @return: org.springframework.amqp.core.Queue
     */
    @Bean
    public Queue autoDeleteQueue() {
        return new AnonymousQueue();
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitCons.Exchange.FANOUT_EXCHANGE);
    }

    @Bean
    public Binding binding(@Qualifier("fanoutExchange") FanoutExchange fanout, @Qualifier("autoDeleteQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(fanout);
    }
}