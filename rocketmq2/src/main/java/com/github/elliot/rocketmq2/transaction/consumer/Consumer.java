package com.github.elliot.rocketmq2.transaction.consumer;

import com.github.elliot.rocketmq2.constant.GroupConstant;
import com.github.elliot.rocketmq2.constant.TopicConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Consumer {

    @Autowired
    private OrderListener orderListener;

    @PostConstruct
    public void init() throws MQClientException {
        DefaultMQPushConsumer consumer = new
                DefaultMQPushConsumer(GroupConstant.TEST_TRANSACTION_CREATE_ORDER_CONSUMER_GROUP);
        consumer.setNamesrvAddr("10.211.55.4:9876");
        consumer.subscribe(TopicConstant.TEST_TRANSACTION_CREATE_ORDER_TOPIC,"*");
        consumer.registerMessageListener(orderListener);
        consumer.start();
    }
}
