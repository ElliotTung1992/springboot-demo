package com.github.elliot.rocketmq2.consumer.orderly;

import com.github.elliot.rocketmq2.constant.GroupConstant;
import com.github.elliot.rocketmq2.constant.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = TopicConstant.TEST_TOPIC_ORDERLY,
        consumerGroup = GroupConstant.CONSUMER_ORDERLY_TEST_GROUP,
        messageModel = MessageModel.CLUSTERING
        //consumeMode = ConsumeMode.ORDERLY
)
public class OrderlyConsumerTwoListener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {


    @Override
    public void onMessage(MessageExt messageExt) {
        byte[] body = messageExt.getBody();
        log.info("{} receive message:{}", this.getClass().getName(), new String(body));
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        //defaultMQPushConsumer.setInstanceName(TopicInstant.TEST_TOPIC);
    }
}