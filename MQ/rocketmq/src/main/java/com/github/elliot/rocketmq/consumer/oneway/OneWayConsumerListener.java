package com.github.elliot.rocketmq.consumer.oneway;

import com.github.elliot.rocketmq.constant.GroupConstant;
import com.github.elliot.rocketmq.constant.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = TopicConstant.ONE_WAY_TOPIC,
        consumerGroup = GroupConstant.CONSUMER_ONE_WAY_GROUP,
        messageModel = MessageModel.CLUSTERING
)
public class OneWayConsumerListener implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(String message) {
        log.info("{} receive one way message:{}", this.getClass().getName(), message);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {

    }
}