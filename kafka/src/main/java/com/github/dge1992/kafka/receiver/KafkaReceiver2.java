package com.github.dge1992.kafka.receiver;

import com.github.dge1992.kafka.utils.KafkaConstants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaReceiver2 {

    // 配置监听的主体，groupId 和配置文件中的保持一致
    @KafkaListener(topics = KafkaConstants.DEFAULT_TOPIC, groupId = KafkaConstants.DEFAULT_GROUP)
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("KafkaReceiver2:" + message);
        }
    }
}
