package com.github.elliot.rocketmq2.transaction.consumer;

import com.alibaba.fastjson.JSONObject;
import com.github.elliot.rocketmq2.transaction.DTO.OrderDTO;
import com.github.elliot.rocketmq2.transaction.service.PointsService;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderListener implements MessageListenerConcurrently {

    @Autowired
    PointsService pointsService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
        logger.info("消费者线程监听到消息");
        try{
            for (MessageExt message:list) {
                int reconsumeTimes = message.getReconsumeTimes();
                logger.info("重复消费次数:{}", reconsumeTimes);
                OrderDTO order  = JSONObject.parseObject(message.getBody(), OrderDTO.class);
                pointsService.increasePoints(order);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }catch (Exception e){
            logger.error("处理消费者数据发生异常 {}", e.getMessage(), e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }

}
