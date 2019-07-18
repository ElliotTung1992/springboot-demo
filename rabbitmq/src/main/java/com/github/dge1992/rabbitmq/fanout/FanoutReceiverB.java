package com.github.dge1992.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/18
 **/
@Component
@RabbitListener(queues = FanoutRabbitConfig.fanoutB)
public class FanoutReceiverB {

    @RabbitHandler
    public void process(String message){
        System.out.println("Receiver form fanout.B: "+message);
    }
}
