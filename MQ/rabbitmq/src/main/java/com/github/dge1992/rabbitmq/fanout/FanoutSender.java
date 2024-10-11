package com.github.dge1992.rabbitmq.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/18
 **/
@Component
public class FanoutSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(){
        String context = "我是一条广播消息";
        System.out.println("Sender : " + context);
        //这里使用了A、B、C三个队列绑定到Fanout交换机上面，发送端的routing_key写任何字符都会被忽略：
        amqpTemplate.convertAndSend("fanoutExchange","", context);
    }
}
