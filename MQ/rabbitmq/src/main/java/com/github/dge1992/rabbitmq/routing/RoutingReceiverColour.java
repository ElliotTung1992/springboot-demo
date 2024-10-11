package com.github.dge1992.rabbitmq.routing;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/18
 **/
@Component
@RabbitListener(queues = RoutingRabbitConfig.colour)
public class RoutingReceiverColour {

    @RabbitHandler
    public void process(String message){
        System.out.println("RoutingReceiverColour接收到消息: "+message);
    }
}
