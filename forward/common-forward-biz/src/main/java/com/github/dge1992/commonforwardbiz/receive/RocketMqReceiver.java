package com.github.dge1992.commonforwardbiz.receive;

import com.alibaba.fastjson.JSON;
import com.github.dge1992.commonforwardapi.model.CommonReceiveRequest;
import com.github.dge1992.commonforwardbiz.template.BaseForwardTemplate;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dge
 * @date 2021-01-04 22:44
 * RocketMq远程调用接收者
 */
@Service
@RocketMQMessageListener(topic = "dgeTopicTest", consumerGroup = "my-group",
        messageModel = MessageModel.CLUSTERING)
public class RocketMqReceiver implements RocketMQListener<String> {

    private Logger logger = LoggerFactory.getLogger(RocketMqReceiver.class);

    @Autowired
    private BaseForwardTemplate baseForwardTemplate;

    @Override
    public void onMessage(String messageStr) {

        logger.info("RocketMQReceiver | onMessage | messageStr:{}", JSON.toJSONString(messageStr));

        //转换
        try {
            CommonReceiveRequest receiveObject = JSON.parseObject(messageStr, CommonReceiveRequest.class);
            baseForwardTemplate.forward(receiveObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
