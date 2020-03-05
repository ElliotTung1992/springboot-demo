package com.github.dge1992.rocketmq.consumer;

import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author 小眼睛带鱼
 * @date 2019-11-19 13:28
 * @desc 消费者
 *
 * CLUSTERING: 一条消息只会被同组的一个订阅者消费
 * BROADCASTING: 一条消息会被所有订阅的消费者消费,此时Group配置不起作用
 *
 */
//@Log4j2
//@Service
//@RocketMQMessageListener(topic = "TopicTest", consumerGroup = "my-group",
//                            messageModel = MessageModel.CLUSTERING)
//public class OneTest2Consumer implements RocketMQListener<String> {
//
//    @Override
//    public void onMessage(String s) {
//        log.info("topic是：TopicTest");
//        log.info("consumerGroup是：my-group");
//        log.info("OneTest2Consumer 收到的消息是：" + s);
//    }
//}
