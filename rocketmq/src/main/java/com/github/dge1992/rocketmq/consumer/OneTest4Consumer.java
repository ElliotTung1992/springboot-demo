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
 */
//@Log4j2
//@Service
//@RocketMQMessageListener(topic = "TopicTest2", consumerGroup = "my-group2",
//                            messageModel = MessageModel.BROADCASTING)
//public class OneTest4Consumer implements RocketMQListener<String> {
//
//    @Override
//    public void onMessage(String s) {
//        log.info("topic是：TopicTest2");
//        log.info("consumerGroup是：my-group2");
//        log.info("OneTest4Consumer 收到的消息是：" + s);
//    }
//}
