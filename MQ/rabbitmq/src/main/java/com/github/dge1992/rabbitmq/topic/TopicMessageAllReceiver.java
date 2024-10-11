package com.github.dge1992.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/18
 **/
@Component
@RabbitListener(queues = TopicRabbitConfig.messageAll)
public class TopicMessageAllReceiver {

    @RabbitHandler
    public void process(String message){
        System.out.println("Receiver " + TopicRabbitConfig.messageAll + ":" + message);
    }
}
