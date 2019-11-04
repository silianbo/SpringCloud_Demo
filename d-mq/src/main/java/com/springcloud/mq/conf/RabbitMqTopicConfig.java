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
 * 主题订阅
 * <p>
 * ------------------------*.orange.*  ->test.topic.orange
 * test.direct.topic------>*.*.black   ->test.topic.black
 * ------------------------green.#     ->test.topic.green
 *
 * @author bo
 */
@Configuration
public class RabbitMqTopicConfig {
    @Bean
    public Queue topicQueueOrange() {
        return new Queue(RabbitCons.QueueName.TOPIC_QUEUE_ORANGE, true);
    }

    @Bean
    public Queue topicQueueBlack() {
        return new Queue(RabbitCons.QueueName.TOPIC_QUEUE_BLACK, true);
    }

    @Bean
    public Queue topicQueueGreen() {
        return new Queue(RabbitCons.QueueName.TOPIC_QUEUE_GREEN, true);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(RabbitCons.Exchange.TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingTopicQueueOrange(@Qualifier("topicQueueOrange") Queue queue, @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitCons.Routingkey.TOPIC_ROUTINGKEY_ORANGE);
    }

    @Bean
    Binding bindingTopicQueueBlack(@Qualifier("topicQueueBlack") Queue queue, @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitCons.Routingkey.TOPIC_ROUTINGKEY_BLACK);
    }

    @Bean
    Binding bindingTopicQueueGreen(@Qualifier("topicQueueGreen") Queue queue, @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitCons.Routingkey.TOPIC_ROUTINGKEY_GREEN);
    }
}