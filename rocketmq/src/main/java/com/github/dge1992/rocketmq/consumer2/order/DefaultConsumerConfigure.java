package com.github.dge1992.rocketmq.consumer2.order;

import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author 小眼睛带鱼
 * @date 2019-11-19 18:47
 * @desc
 */
@Configuration
@Log4j2
public abstract class DefaultConsumerConfigure {

    @Value("${rocketmq.name-server}")
    private String namesrvAddr;

    // 开启消费者监听服务
    public void listener(String topic, String tag) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my-group");

        consumer.setNamesrvAddr(namesrvAddr);

        //consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe(topic, tag);

        // 开启内部类实现监听
        consumer.registerMessageListener(new MessageListenerOrderly() {

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                return DefaultConsumerConfigure.this.dealBody(msgs);
            }
        });

        consumer.start();
    }

    // 处理body的业务
    public abstract ConsumeOrderlyStatus dealBody(List<MessageExt> msgs);
}
