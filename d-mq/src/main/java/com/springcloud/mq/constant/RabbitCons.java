package com.springcloud.mq.constant;

/**
 * @author bo
 */
public class RabbitCons {
    /**
     * 消息队列名称
     */
    public interface QueueName {
        /**
         * 广播
         */
        String FANOUT_QUEUENAME = "test.direct.Fanout";
        /**
         * 直连
         */
        String DIRECT_QUEUE_ORANGE = "test.direct.orange";
        String DIRECT_QUEUE_BLACK_1 = "test.direct.black.1";
        String DIRECT_QUEUE_BLACK_2 = "test.direct.black.2";
        String DIRECT_QUEUE_GREEN = "test.direct.green";

        /**
         * 订阅
         */
        String TOPIC_QUEUE_ORANGE = "test.topic.orange";
        String TOPIC_QUEUE_BLACK = "test.topic.black";
        String TOPIC_QUEUE_GREEN = "test.topic.green";
    }

    /**
     * 交换器名称
     */
    public interface Exchange {
        /**
         * 广播交换
         */
        String FANOUT_EXCHANGE = "test.fanout.exchange";

        /**
         * 直连交换
         */
        String DIRECT_EXCHANGE = "test.direct.exchange";

        /**
         * 主题交换
         */
        String TOPIC_EXCHANGE = "test.topic.exchange";
    }

    /**
     * 绑定关系
     */
    public interface Routingkey {
        /**
         * 直连
         */
        String DIRECT_ROUTINGKEY_ORANGE = "orange";
        String DIRECT_ROUTINGKEY_BLACK = "black";
        String DIRECT_ROUTINGKEY_GREEN = "green";

        /**
         * 订阅
         */
        String TOPIC_ROUTINGKEY_ORANGE = "*.orange.*";
        String TOPIC_ROUTINGKEY_BLACK = "*.*.black";
        String TOPIC_ROUTINGKEY_GREEN = "green.#";
    }
}
