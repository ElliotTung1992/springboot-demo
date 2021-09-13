package com.github.dge1992.fish.reactive.impl;

import com.github.dge1992.fish.reactive.FlowService;
import org.springframework.stereotype.Service;

/*import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

*//**
 * @author dge
 * @version 1.0
 * @date 2021-03-08 11:24
 *//*
@Service
public class FlowServiceImpl implements FlowService {

    @Override
    public void execute() {
        // 定义发布者
        SubmissionPublisher publisher = new SubmissionPublisher<Integer>();

        // 定义处理器
        MyProcessor myProcessor = new MyProcessor();

        // 发布者和处理器建立关系
        publisher.subscribe(myProcessor);

        // 定义最终订阅者，消费String类型数据
        Flow.Subscriber subscriber = new Flow.Subscriber<String>() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {

                System.out.println("订阅者调用onSubscribe");

                //保持订阅关系，需要它来给发布者响应
                this.subscription = subscription;
                //请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                //接收到数据，进行处理
                System.out.println("订阅者接收到数据：" + item);

                //处理完请求，调用request再请求一个数据
                this.subscription.request(5);
            }

            @Override
            public void onError(Throwable throwable) {
                //打印异常
                throwable.printStackTrace();

                //告诉发布者，消费者不接受请求了
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("订阅者处理完数据了！！！");
            }
        };

        // 处理器和最终订阅者建立关系
        myProcessor.subscribe(subscriber);

        // 生产数据并发布
        publisher.submit(-111);
        publisher.submit(111);
        publisher.submit(222);
        publisher.submit(333);
        publisher.submit(444);

        // 结束后，关闭发布者
        publisher.close();

        try {
            Thread.currentThread().join(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    *//**
     * 自定义处理器
     * @author dge
     * @date 2021-03-08 16:40
     *//*
    class MyProcessor extends SubmissionPublisher<String>
            implements Flow.Processor<Integer, String> {

        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            //保持订阅关系，需要它来给发布者响应
            this.subscription = subscription;
            //请求一个数据
            this.subscription.request(1);
        }

        @Override
        public void onNext(Integer item) {
            //接收到数据，进行处理
            System.out.println("处理器接收到数据：" + item);

            //过滤掉小于0的数据，然后发布出去
            if(item > 0){
                this.submit("转换后的数据" + item);
            }

            //处理完请求，调用request再请求一个数据
            this.subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            //打印异常
            throwable.printStackTrace();

            //告诉发布者，消费者不接受请求了
            this.subscription.cancel();
        }

        @Override
        public void onComplete() {

            System.out.println("处理器处理完数据了！！！");

            // 关闭发布者
            this.close();
        }
    }
}*/
