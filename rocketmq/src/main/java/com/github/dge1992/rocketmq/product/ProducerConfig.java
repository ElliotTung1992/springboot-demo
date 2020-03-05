package com.github.dge1992.rocketmq.product;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小眼睛带鱼
 * @date 2019-11-28 14:02
 * @desc 生产者配置
 */
@Configuration
public class ProducerConfig {

    @Value("${rocketmq.name-server}")
    private String namesrvAddr;

    @Bean(name = "myGroupProduct")
    public DefaultMQProducer producer() {
        DefaultMQProducer producer = new DefaultMQProducer("my-group");
        //生产者发送失败设置重试
        producer.setRetryTimesWhenSendFailed(4);
        //3秒内没有发送成功就会重试
        producer.setSendMsgTimeout(3000);
        producer.setNamesrvAddr(namesrvAddr);
        return producer;
    }
}
