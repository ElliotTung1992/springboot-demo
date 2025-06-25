package com.elliot.github.olivers.domain;

import com.elliot.github.olivers.enums.OrderStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {

    private Integer id;

    /**
     * 总价
     */
    private BigDecimal amount;

    /**
     * 订单状态
     */
    private OrderStatusEnum orderStatusEnum;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date shipTime;
}
