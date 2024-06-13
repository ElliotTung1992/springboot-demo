package com.github.elliot.rocketmq.transaction.domain;

import lombok.Data;

@Data
public class OrderDTO {

    private Long id;

    private String orderNo;

    private String commodityCode;
}
