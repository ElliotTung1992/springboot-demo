package com.github.elliot.rocketmq.consumer.tag;

import com.github.elliot.rocketmq.constant.GroupConstant;
import com.github.elliot.rocketmq.constant.TagConstant;
import com.github.elliot.rocketmq.constant.TopicConstant;
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
@RocketMQMessageListener(topic = TopicConstant.TEST_TAG_TOPIC,
        consumerGroup = GroupConstant.CONSUMER_TAG_TEST_GROUP,
        selectorExpression = TagConstant.OK
        //messageModel = MessageModel.BROADCASTING
)
public class TestGroupAndTagConsumerOneListener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {


    @Override
    public void onMessage(MessageExt messageExt) {
        String tags = messageExt.getTags();
        byte[] body = messageExt.getBody();
        log.info("{} tag:{} receive message:{}", this.getClass().getName(), tags, new String(body));
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.setInstanceName("2");
    }
}