package com.springcloud.mq.conf;

import com.springcloud.mq.constant.RabbitCons;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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

    @Bean
    public Queue fanoutQueue() {
        return new Queue(RabbitCons.QueueName.FANOUT_QUEUENAME, true);
    }


    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitCons.Exchange.FANOUT_EXCHANGE);
    }

    @Bean
    public Binding binding(@Qualifier("fanoutExchange") FanoutExchange fanout, @Qualifier("fanoutQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(fanout);
    }
}