package com.github.elliot.rocketmq.controller;

import com.github.elliot.rocketmq.constant.TagConstant;
import com.github.elliot.rocketmq.constant.TopicConstant;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
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
        SendResult sendResult = rocketMQTemplate
                .syncSend(TopicConstant.TEST_TOPIC, "A Simple Message");
        System.out.println(sendResult);
    }

    @GetMapping("/sendMessageAndTag")
    public void sendMessageAndTag(){
        SendResult sendResult = rocketMQTemplate
                .syncSend(TopicConstant.TEST_TAG_TOPIC + ":" + TagConstant.OK, "A Simple Message");
        System.out.println(sendResult);
    }

    @GetMapping("/sendMessageByBroadCasting")
    public void sendMessageByBroadCasting(){
        SendResult sendResult = rocketMQTemplate
                .syncSend(TopicConstant.TEST_BROADCASTING_TOPIC, "A Simple Message");
        System.out.println(sendResult);
    }

    @GetMapping("/sendBatchMessage")
    public void sendBatchMessage(){
        for (int i = 0; i < 30; i++) {
            rocketMQTemplate.syncSend(TopicConstant.TEST_TOPIC, "A Simple Message" + i);
        }
    }

    @GetMapping("/sendOrderlyMessage")
    public void sendOrderlyMessage(){
        for (int i = 0; i < 30; i++) {
            // rocketMQTemplate.syncSend(TopicConstant.TEST_TOPIC_ORDERLY, "A Simple Message" + i);
            rocketMQTemplate.syncSendOrderly(TopicConstant.TEST_TOPIC_ORDERLY, "A Simple Message" + i, "Elliot");
        }
    }
}
