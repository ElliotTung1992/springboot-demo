package com.github.elliot.rocketmq.controller;

import com.github.elliot.rocketmq.constant.TagConstant;
import com.github.elliot.rocketmq.constant.TopicConstant;
import com.github.elliot.rocketmq.domain.Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ProductController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送同步消息
     */
    @GetMapping("/sendMessage")
    public void sendMessage(){
        SendResult sendResult = rocketMQTemplate
                .syncSend(TopicConstant.TEST_TOPIC, "A Simple Message");
        System.out.println(sendResult);
    }

    /**
     * 发送标签消息
     */
    @GetMapping("/sendMessageAndTag")
    public void sendMessageAndTag(){
        SendResult sendResult = rocketMQTemplate
                .syncSend(TopicConstant.TEST_TAG_TOPIC + ":" + TagConstant.OK, "A Simple Message");
        System.out.println(sendResult);
    }

    /**
     * 发送广播消息
     */
    @GetMapping("/sendMessageByBroadCasting")
    public void sendMessageByBroadCasting(){
        SendResult sendResult = rocketMQTemplate
                .syncSend(TopicConstant.TEST_BROADCASTING_TOPIC, "A Simple Message");
        System.out.println(sendResult);
    }

    /**
     * 同步批量发送消息
     */
    @GetMapping("/sendBatchMessage")
    public void sendBatchMessage(){
        for (int i = 0; i < 30; i++) {
            rocketMQTemplate.syncSend(TopicConstant.TEST_TOPIC, "A Simple Message" + i);
        }
    }

    /**
     * 发送顺序消息
     */
    @GetMapping("/sendOrderlyMessage")
    public void sendOrderlyMessage(){
        for (int i = 0; i < 30; i++) {
            // rocketMQTemplate.syncSend(TopicConstant.TEST_TOPIC_ORDERLY, "A Simple Message" + i);
            rocketMQTemplate.syncSendOrderly(TopicConstant.TEST_TOPIC_ORDERLY, "A Simple Message" + i, "Elliot");
        }
    }

    /**
     * 发送延时消息
     */
    @GetMapping("/sendDelayMessage")
    public void sendDelayMessage(){
        Message message = new Message();
        message.setMessageName("hello world");
        message.setCreateTime(LocalDateTime.now());
        rocketMQTemplate.syncSendDelayTimeSeconds(TopicConstant.TEST_DELAY_TOPIC, message, 10);
    }

    /**
     * 发送一步消息
     */
    @GetMapping("/sendAsyncMessage")
    public void sendAsyncMessage(){
        rocketMQTemplate.asyncSend(TopicConstant.TEST_ASYNC_TOPIC, "A Async Message", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("onSuccess");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("onException");
            }
        });
    }

    /**
     * 发送单向消息
     */
    @GetMapping("/sendOneWayMessage")
    public void sendOneWayMessage(){
        rocketMQTemplate.sendOneWay(TopicConstant.ONE_WAY_TOPIC, "A OneWay Message");
    }

}
