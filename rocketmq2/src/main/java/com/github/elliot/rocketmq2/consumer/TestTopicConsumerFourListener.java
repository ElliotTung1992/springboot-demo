package com.github.elliot.rocketmq2.consumer;

import com.github.elliot.rocketmq2.constant.GroupConstant;
import com.github.elliot.rocketmq2.constant.TagConstant;
import com.github.elliot.rocketmq2.constant.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = TopicConstant.TEST_BROADCASTING_TOPIC,
        consumerGroup = GroupConstant.CONSUMER_TEST_BROADCASTING_GROUP_ELLIOT,
        messageModel = MessageModel.BROADCASTING)
public class TestTopicConsumerFourListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("{} receive message:{}", this.getClass().getName(), message);
    }
}