package com.github.dge1992.rocketmq;

import com.github.dge1992.common.domain.User;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author 小眼睛带鱼
 * @date 2019-11-19 10:37
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RocketMqProducerTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    //@Test
    public void testRocketMq1() {
        //rocketMQTemplate.convertAndSend("test-topic-1", "我是一条测试数据");
        IntStream.range(0, 10).forEach(e -> {
            MessageBuilder<User> messageBuilder = MessageBuilder.withPayload(new User(e, "dge", 10 + e));
            rocketMQTemplate.send("test-topic-2", messageBuilder.build());
        });
    }

    @Test
    public void testProducer() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("my-group1");
        producer.setNamesrvAddr("47.100.220.23:9876");
        producer.start();
        for (int i = 0; i < 10; i++){
            Message msg = new Message("TopicTest",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }

    @Test
    public void testConsumer() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my-group1");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setNamesrvAddr("47.100.220.23:9876");
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.subscribe("TopicTest", "TagA");
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.printf("Broadcast Consumer Started.%n");
    }


}
