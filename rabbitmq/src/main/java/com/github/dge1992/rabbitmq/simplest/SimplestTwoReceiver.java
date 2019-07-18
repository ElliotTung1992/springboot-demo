package com.github.dge1992.rabbitmq.simplest;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/18
 **/
@Component
@RabbitListener(queues = "SimplestMessage")
public class SimplestTwoReceiver {

    @RabbitHandler
    public void process(String message){
        System.out.println("SimplestTwoReceiver:"+message);
    }
}
