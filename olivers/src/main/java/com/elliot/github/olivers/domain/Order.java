package com.elliot.github.olivers.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("mall_order")
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
    private String orderStatus;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date shipTime;
}
