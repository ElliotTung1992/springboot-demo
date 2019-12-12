package com.github.dge1992.rocketmq.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author 小眼睛带鱼
 * @date 2019-11-19 13:28
 * @desc
 */
@Service
@RocketMQMessageListener(topic = "TopicTest", consumerGroup = "my-group")
public class Test2Consumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println("2收到的消息是：" + s);
    }
}
