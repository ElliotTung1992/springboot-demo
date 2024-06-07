package com.github.elliot.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = "${rocketmq.topic}", consumerGroup = "${rocketmq.consumer.group}")
public class BaseConsumerListener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {


    @Override
    public void onMessage(MessageExt messageExt) {
        System.out.println(messageExt);
        byte[] body = messageExt.getBody();
        String s = new String(body);
        System.out.println(s);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        System.out.println(defaultMQPushConsumer);
    }
}