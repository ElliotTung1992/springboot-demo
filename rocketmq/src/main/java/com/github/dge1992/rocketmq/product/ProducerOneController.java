package com.github.dge1992.rocketmq.product;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小眼睛带鱼
 * @date 2019-11-28 14:04
 * @desc
 */
@RestController
public class ProducerOneController {

    @Resource(name = "myGroupProduct")
    private DefaultMQProducer producer;

    @RequestMapping("/sendMessage")
    public void sendMessage() throws Exception {
        Message message = new Message();
        message.setTopic("TopicTest");
        message.setBody("hello rocketmq".getBytes());
        producer.send(message);
    }

    @RequestMapping("/sendMessageTwo")
    public void sendMessageTwo() throws Exception {
        Message message = new Message();
        message.setTopic("TopicTest2");
        message.setBody("hello rocketmq".getBytes());
        producer.send(message);
    }

}
