package com.github.dge1992.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小眼睛带鱼
 * @date 2019-11-28 14:02
 * @desc
 */
@Configuration
public class ProducerConfig {

    @Value("${rocketmq.name-server}")
    private String namesrvAddr;

    @Bean
    public DefaultMQProducer producer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("my-group");
        producer.setRetryTimesWhenSendFailed(4);
        //producer.setRetryTimesWhenSendAsyncFailed(4);
        producer.setSendMsgTimeout(3000);
        producer.setNamesrvAddr(namesrvAddr);
        return producer;
    }
}
