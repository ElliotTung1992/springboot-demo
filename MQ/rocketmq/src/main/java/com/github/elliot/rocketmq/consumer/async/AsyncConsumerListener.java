package com.github.elliot.rocketmq.consumer.async;

import com.github.elliot.rocketmq.constant.GroupConstant;
import com.github.elliot.rocketmq.constant.TopicConstant;
import com.github.elliot.rocketmq.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RocketMQMessageListener(topic = TopicConstant.TEST_ASYNC_TOPIC,
        consumerGroup = GroupConstant.CONSUMER_TEST_ASYNC_GROUP,
        messageModel = MessageModel.CLUSTERING
)
public class AsyncConsumerListener implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(String message) {
        log.info("{} receive message:{}", this.getClass().getName(), message);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {

    }
}