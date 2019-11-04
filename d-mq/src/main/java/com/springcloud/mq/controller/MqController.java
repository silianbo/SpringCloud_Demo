package com.springcloud.mq.controller;

import com.springcloud.mq.rabbitmq.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.springcloud.mq.constant.RabbitCons.Exchange.FANOUT_EXCHANGE;

/**
 * @author bo
 */
@RestController
@RequestMapping("/api/rabbitmq")
public class MqController {
    @Autowired
    private RabbitSender rabbitSender;

    @RequestMapping(value = "/v1/notic", method = RequestMethod.POST)
    public String sendNotic(String msg) {
        try {
            rabbitSender.send(FANOUT_EXCHANGE, "", "广播接口 " + msg);
            return "{\"code\": \"1\",\"msg\": \"发送成功:" + msg + "\"}";
        } catch (Exception e) {
            return "{\"code\": \"1\",\"msg\": \"发送异常:" + e.getMessage() + "\"}";
        }
    }
}
