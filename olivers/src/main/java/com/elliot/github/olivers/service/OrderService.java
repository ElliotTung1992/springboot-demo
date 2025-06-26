package com.elliot.github.olivers.service;

import com.elliot.github.olivers.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void creatOrder();

    /**
     * 支付订单
     */
    void payOrder();

    /**
     * 查询订单
     */
    Order queryOrder();

    void update();
}
