package com.github.dge1992.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小眼睛带鱼
 * @date 2019-11-28 14:04
 * @desc
 */
@RestController
public class Producer2Controller {

    @Autowired
    private DefaultMQProducer producer;

    @RequestMapping("/sendMessage")
    public void sendMessage() throws Exception {
        Message message = new Message();
        message.setTopic("TopicTest");
        message.setBody("hello rocketmq".getBytes());
        producer.send(message);
    }

}
