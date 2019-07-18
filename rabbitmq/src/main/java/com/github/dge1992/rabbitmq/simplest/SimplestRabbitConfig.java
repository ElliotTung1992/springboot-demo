package com.github.dge1992.rabbitmq.simplest;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/18
 **/
@Configuration
public class SimplestRabbitConfig {

    @Bean
    public Queue queue(){
        return new Queue("SimplestMessage");
    }
}
