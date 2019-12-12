package com.github.dge1992.rocketmq.consumer2.concurrent;

import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
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

        //设置消费起始时间，可以用于消息回溯
        //consumer.setConsumeTimestamp("20191126000000");

        consumer.subscribe(topic, tag);

        // 开启内部类实现监听
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                msgs.stream().forEach(e -> System.out.println(e.getReconsumeTimes()));
                int i = 10 / 0;
                return DefaultConsumerConfigure.this.dealBody(msgs);
            }
        });

        consumer.start();
    }

    // 处理body的业务
    public abstract ConsumeConcurrentlyStatus dealBody(List<MessageExt> msgs);
}
