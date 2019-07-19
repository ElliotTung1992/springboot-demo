package com.github.dge1992.rabbitmq.routing;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/18
 **/
@Configuration
public class RoutingRabbitConfig {

    final static String fruits = "fruits";
    final static String colour = "colour";

    //创建三个队列
    @Bean
    public Queue routingFruits() {
        return new Queue(RoutingRabbitConfig.fruits);
    }

    @Bean
    public Queue routingColour() {
        return new Queue(RoutingRabbitConfig.colour);
    }


    //创建exchange
    @Bean
    public DirectExchange routingExchange() {
        return new DirectExchange("routingExchange");
    }

    @Bean
    public Binding bindingOrange(DirectExchange routingExchange, Queue routingFruits) {
        return BindingBuilder.bind(routingFruits).to(routingExchange).with("orange");
    }

    @Bean
    public Binding bindingBanana(DirectExchange routingExchange, Queue routingFruits) {
        return BindingBuilder.bind(routingFruits).to(routingExchange).with("banana");
    }

    @Bean
    public Binding bindingGreen(DirectExchange routingExchange, Queue routingColour) {
        return BindingBuilder.bind(routingColour).to(routingExchange).with("green");
    }

    @Bean
    public Binding bindingYellow(DirectExchange routingExchange, Queue routingColour) {
        return BindingBuilder.bind(routingColour).to(routingExchange).with("yellow");
    }

}
