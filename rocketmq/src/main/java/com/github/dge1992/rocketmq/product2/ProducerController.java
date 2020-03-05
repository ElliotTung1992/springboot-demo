package com.github.dge1992.rocketmq.product2;

import com.github.dge1992.rocketmq.consumer2.transaction.TransactionListenerImpl;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 小眼睛带鱼
 * @date 2019-11-25 11:09
 * @desc
 */
@RestController
public class ProducerController {

    @Value("${rocketmq.name-server}")
    private String namesrvAddr;

    @RequestMapping("/test")
    public void test() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my-group");
        producer.setNamesrvAddr(namesrvAddr);
        producer.start();
        for (int i = 0; i < 10; i++){
            Message msg = new Message("TopicTest",
                    "tagA",
                    "OrderID188",
                    ("Hello world 1-" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);

            Message msg2 = new Message("TopicTest",
                    "tagB",
                    "OrderID188",
                    ("Hello world 2-" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult2 = producer.send(msg2);
        }
        producer.shutdown();
    }

    //延时发送消息
    @RequestMapping("/test2")
    public void test2() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my-group");
        producer.setNamesrvAddr(namesrvAddr);
        producer.start();
        for (int i = 0; i < 10; i++){
            Message message = new Message("TopicTest", ("Hello scheduled message " + i).getBytes());
            // This message will be delivered to consumer 10 seconds later.
            message.setDelayTimeLevel(3);
            // Send the message
            producer.send(message);
        }
        producer.shutdown();
    }

    //异步发送消息
    @RequestMapping("/test3")
    public void test3() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my-group");
        producer.setNamesrvAddr(namesrvAddr);
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++){
            final int index = i;
            Message msg = new Message("TopicTest",
                    "",
                    "OrderID188",
                    ("Hello world").getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println(e);
                }
            });
        }
        countDownLatch.await(5, TimeUnit.SECONDS);
        producer.shutdown();
    }

    //单向发送（只发送请求不等待应答）
    @RequestMapping("/test4")
    public void test4() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my-group");
        producer.setNamesrvAddr(namesrvAddr);
        producer.start();
        for (int i = 0; i < 10; i++){
            final int index = i;
            Message msg = new Message("TopicTest",
                    "",
                    "OrderID188",
                    ("Hello world").getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(msg);
        }
        producer.shutdown();
    }

    //批处理
    @RequestMapping("/test5")
    public void test5() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my-group");
        producer.setNamesrvAddr(namesrvAddr);
        producer.start();

        //producer.setInstanceName("aaaa");

        String topic = "TopicTest";
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(topic, "", "OrderID001", "Hello world 0".getBytes()));
        messages.add(new Message(topic, "", "OrderID002", "Hello world 1".getBytes()));
        messages.add(new Message(topic, "", "OrderID003", "Hello world 2".getBytes()));

        ListSplitter splitter = new ListSplitter(messages);
        while (splitter.hasNext()) {
            try {
                List<Message>  listItem = splitter.next();
                producer.send(listItem);
            } catch (Exception e) {
                e.printStackTrace();
                //handle the error
            }
        }
        producer.shutdown();
    }

    @RequestMapping("/test6")
    public void test6() throws Exception {
        TransactionListener transactionListener = new TransactionListenerImpl();
        TransactionMQProducer producer = new TransactionMQProducer("my-group");
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });

        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();

        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        //for (int i = 0; i < 10; i++) {
            try {
                Message msg =
                        new Message("TopicTest", "", "KEY",
                                ("Hello RocketMQ ").getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                System.out.printf("%s%n", sendResult);

                Thread.sleep(10);
            } catch (MQClientException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        //}

        Thread.sleep(Integer.MAX_VALUE);
        producer.shutdown();
    }
}
