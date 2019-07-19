package com.github.dge1992.rabbitmq;

import com.github.dge1992.rabbitmq.fanout.FanoutSender;
import com.github.dge1992.rabbitmq.routing.RoutingSender;
import com.github.dge1992.rabbitmq.workqueue.SimplestSender;
import com.github.dge1992.rabbitmq.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTestsTwo {

    @Autowired
    SimplestSender simplestSender;

    @Autowired
    TopicSender topicSender;

    @Autowired
    FanoutSender fanoutSender;

    @Autowired
    RoutingSender routingSender;

    /**
     * @author dongganen
     * @date 2019/7/18
     * @desc: 简单消息队列
     * 特点：
     * 1.一条消息只能被消费一次
     * 2.当有多个消费者时，轮询消费消息
     */
    @Test
    public void simplestSenderTest() {
        IntStream.range(0, 10).forEach(e -> {
            simplestSender.sendTwo(e);
            simplestSender.sendOne();
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * @author dongganen
     * @date 2019/7/18
     * @desc: 消息先发送到交换机，交换机根据规则发送到指定队列，
     *      一条消息可以发送到多个匹配到的队列，最后消费者消费指定队列
     */
    @Test
    public void topicSenderTest() {
        topicSender.sendOne();
        topicSender.sendTwo();
        topicSender.sendThree();
    }

    /**
     * @author dongganen
     * @date 2019/7/18
     * @desc: 消息发送到广播交换机，与广播交换机绑定的队列都会接收到消息，
     *        最后消费者消费指定队列
     */
    @Test
    public void fanoutSenderTest() {
        fanoutSender.send();
    }

    @Test
    public void routingSenderTest() {
        routingSender.sendOrange();
        routingSender.sendGreen();
    }

}
