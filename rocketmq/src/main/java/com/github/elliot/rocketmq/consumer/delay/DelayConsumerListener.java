package com.github.elliot.rocketmq.consumer.delay;

import com.github.elliot.rocketmq.constant.GroupConstant;
import com.github.elliot.rocketmq.constant.TopicConstant;
import com.github.elliot.rocketmq.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RocketMQMessageListener(topic = TopicConstant.TEST_DELAY_TOPIC,
        consumerGroup = GroupConstant.CONSUMER_TEST_DELAY_GROUP,
        messageModel = MessageModel.CLUSTERING
)
public class DelayConsumerListener implements RocketMQListener<Message>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(Message message) {
        log.info("{} receive message createTime:{} nowTime:{}",
                this.getClass().getName(), message.getCreateTime(), LocalDateTime.now());
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {

    }
}