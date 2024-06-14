package com.github.elliot.rocketmq2.transaction.DTO;

import lombok.Data;

@Data
public class OrderDTO {

    private Long id;

    private String orderNo;

    private String commodityCode;

    private Long userId;

    private Double amount;
}
