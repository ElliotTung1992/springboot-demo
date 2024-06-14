package com.github.elliot.rocketmq2.transaction.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Consumer {

    String consumerGroup = "consumer-group";

    DefaultMQPushConsumer consumer;

    @Autowired
    private OrderListener orderListener;

    @PostConstruct
    public void init() throws MQClientException {
        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr("10.211.55.4:9876");
        consumer.subscribe("order","*");
        consumer.registerMessageListener(orderListener);
        consumer.start();
    }
}
