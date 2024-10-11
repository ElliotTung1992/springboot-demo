package com.github.dge1992.kafka.controller;

import com.github.dge1992.kafka.utils.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * kafka控制器
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/sendMessage")
    public Object sendMessage(){
        kafkaTemplate.send(KafkaConstants.DEFAULT_TOPIC, "测试");
        return "发送kafka消息！！！";
    }
}
