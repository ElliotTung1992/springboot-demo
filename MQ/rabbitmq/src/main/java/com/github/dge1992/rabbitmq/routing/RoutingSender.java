package com.github.dge1992.rabbitmq.routing;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/18
 **/
@Component
public class RoutingSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendOrange(){
        String context = "我是一条路由消息我喜欢吃orange";
        System.out.println("Sender : " + context);
        amqpTemplate.convertAndSend("routingExchange","orange", context);
    }

    public void sendGreen(){
        String context = "我是一条路由消息我喜欢绿色";
        System.out.println("Sender : " + context);
        amqpTemplate.convertAndSend("routingExchange","green", context);
    }
}
