package com.github.elliot.rocketmq.transaction.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;

@TableName("t_order")
@Data
public class Order {

    @Id
    private Long id;

    private String orderNo;

    private String commodityCode;
}
