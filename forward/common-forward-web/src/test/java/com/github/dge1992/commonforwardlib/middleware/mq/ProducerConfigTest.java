package com.github.dge1992.commonforwardlib.middleware.mq;


import com.alibaba.fastjson.JSON;
import com.github.dge1992.CommonForwardWebApplicationTests;
import com.github.dge1992.commonforwardapi.model.CommonReceiveObject;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-22 14:37
 */
class ProducerConfigTest extends CommonForwardWebApplicationTests {

    @Autowired
    private DefaultMQProducer producer;

    @Test
    void test() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        CommonReceiveObject receiveObject = new CommonReceiveObject();
        Message message = new Message();
        message.setTopic("dgeTopicTest");
        message.setBody(JSON.toJSONString(receiveObject).getBytes());
        producer.send(message);

        producer.send(message);

        TimeUnit.SECONDS.sleep(200);
    }
}