package com.github.dge1992.rabbitmq.workqueue;

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
public class SimplestSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendOne(){
        String context = "SimplestMessage-"+ LocalDateTime.now();
        System.out.println("send:"+context);
        //往名称为 hello 的queue中发送消息
        this.amqpTemplate.convertAndSend("SimplestMessage",context);
    }

    //给hello2发送消息,并接受一个计数参数
    public void sendTwo(int i){
        String context = "SimplestMessageTwo-" + i;
        System.out.println("send:"+context);
        //往名称为 hello 的queue中发送消息
        this.amqpTemplate.convertAndSend("SimplestMessage",context);
    }
}
