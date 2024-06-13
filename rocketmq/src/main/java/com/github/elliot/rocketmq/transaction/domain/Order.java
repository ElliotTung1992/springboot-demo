package com.github.elliot.rocketmq.transaction.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("order")
@Data
public class Order {

    private Long id;

    private String orderNo;

    private String commodityCode;
}
