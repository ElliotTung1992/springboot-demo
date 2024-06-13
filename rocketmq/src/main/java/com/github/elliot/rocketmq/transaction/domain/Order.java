package com.github.elliot.rocketmq.transaction.domain;

import lombok.Data;

@Data
public class Order {

    private Long id;

    private String orderNo;

    private String commodityCode;
}
