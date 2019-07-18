package com.github.dge1992.rabbitmq.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/18
 **/
@Configuration
public class FanoutRabbitConfig {

    final static String fanoutA = "fanout.A";
    final static String fanoutB = "fanout.B";
    final static String fanoutC = "fanout.C";

    //创建三个队列
    @Bean
    public Queue fountA() {
        return new Queue(FanoutRabbitConfig.fanoutA);
    }

    @Bean
    public Queue fountB() {
        return new Queue(FanoutRabbitConfig.fanoutB);
    }

    @Bean
    public Queue fountC() {
        return new Queue(FanoutRabbitConfig.fanoutC);
    }

    //创建exchange,指定交换策略
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    //分别给三个队列指定exchange,这里使用了A、B、C三个队列绑定到Fanout交换机上面，发送端的routing_key写任何字符都会被忽略：
    @Bean
    public Binding bindingExchangeA(Queue fountA,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fountA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeB(Queue fountB,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fountB).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeC(Queue fountC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fountC).to(fanoutExchange);
    }
}
