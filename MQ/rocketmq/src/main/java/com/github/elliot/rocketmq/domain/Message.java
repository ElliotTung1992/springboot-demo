package com.github.elliot.rocketmq.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {

    private String messageName;

    private LocalDateTime createTime;
}
