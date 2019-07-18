package com.github.dge1992.rabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/18
 **/
@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendOne(){
        String context = "我是消息messageAOne";
        System.out.println("Sender : " + context);
        amqpTemplate.convertAndSend("topicExchange",TopicRabbitConfig.messageAOne, context);
    }

    public void sendTwo(){
        String context = "我是消息messageATwo";
        System.out.println("Sender : " + context);
        amqpTemplate.convertAndSend("topicExchange",TopicRabbitConfig.messageATwo, context);
    }

    public void sendThree(){
        String context = "我是消息messageBThree";
        System.out.println("Sender : " + context);
        amqpTemplate.convertAndSend("topicExchange",TopicRabbitConfig.messageBThree, context);
    }
}
