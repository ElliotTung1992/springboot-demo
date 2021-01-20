package com.github.dge1992.commonforward.lib.middleware.config;

import com.alibaba.fastjson.JSON;
import com.github.dge1992.commonforward.BaseTestCase;
import com.github.dge1992.commonforward.api.model.CommonReceiveObject;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-04 16:34
 */
public class ProducerTest extends BaseTestCase {

    @Autowired
    private DefaultMQProducer producer;

    @Test
    void test() throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {

        CommonReceiveObject receiveObject = new CommonReceiveObject();

        receiveObject.setFrom("F1");
        receiveObject.setTo("M3");
        receiveObject.setPreStrategyCode("testPre");
        receiveObject.setPostStrategyCode("testPost");
        receiveObject.setMethod(0);
        receiveObject.setURL("http://localhost:8091/user/");

        Message msg = new Message("dgeTopicTest",
                JSON.toJSONString(receiveObject).getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.send(msg);
    }

    @Test
    void testJson() {
        CommonReceiveObject receiveObject = new CommonReceiveObject();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("age", "12");
        receiveObject.setHeadMap(hashMap);
        String messageStr = JSON.toJSONString(receiveObject);

        CommonReceiveObject receiveObject2 = JSON.parseObject(messageStr, CommonReceiveObject.class);
        HashMap<String, String> headMap = receiveObject2.getHeadMap();
        String age = headMap.get("age");
        System.out.println(age);
        System.out.println(JSON.toJSONString(receiveObject2));
        System.out.println(headMap);
    }
}
