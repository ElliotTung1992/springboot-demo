package com.github.elliot.rocketmq.controller;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/sendMessage")
    public void sendMessage(){
        SendResult sendResult
                = rocketMQTemplate.syncSend("springboot-mq", "A Simple Message");
        System.out.println(sendResult);
    }
}
