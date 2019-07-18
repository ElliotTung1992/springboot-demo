package com.github.dge1992.rabbitmq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/18
 **/
@Configuration
public class TopicRabbitConfig {

    final static String messageA = "topic.messageA";
    final static String messageAOne = "topic.messageA.one";
    final static String messageATwo = "topic.messageA.two";
    final static String messageBThree = "topic.messageB.three";
    final static String messageAll = "topic.messageAll";

    //创建三个Queue
    @Bean
    public Queue queueMessageAOne(){
        return new Queue(TopicRabbitConfig.messageAOne);
    }

    @Bean
    public Queue queueMessageATwo(){
        return new Queue(TopicRabbitConfig.messageATwo);
    }

    @Bean
    public Queue queueMessageA(){
        return new Queue(TopicRabbitConfig.messageA);
    }

    @Bean
    public Queue queueMessageAll(){
        return new Queue(TopicRabbitConfig.messageAll);
    }

    //配置TopicExchange,指定名称为 topicExchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }

    //给队列绑定 exchange 和 routing_key
    @Bean
    public Binding bindingExchangeMessageOne(Queue queueMessageAOne, TopicExchange exchange){
        return BindingBuilder.bind(queueMessageAOne).to(exchange).with(TopicRabbitConfig.messageAOne);
    }

    @Bean
    public Binding bingingExchangeMessages(Queue queueMessageA, TopicExchange exchange){
        return BindingBuilder.bind(queueMessageA).to(exchange).with("topic.messageA.*");
    }

    @Bean
    public Binding bingingExchangeMessageT(Queue queueMessageAll, TopicExchange exchange){
        return BindingBuilder.bind(queueMessageAll).to(exchange).with("topic.#");
    }

}
