package com.springcloud.mq.rabbitmq.work;

import com.springcloud.mq.constant.TopicRabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author bo
 */
@Component
@RabbitListener(queues = TopicRabbitConfig.QueueName.QUEUE_NAME_WORK)
public class WorkRabbitReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String msg) throws InterruptedException {
        logger.info("class=WorkRabbitReceiver,op=start_process, msg={}", msg);
        Thread.sleep(10 * 60 * 1000);
    }
}