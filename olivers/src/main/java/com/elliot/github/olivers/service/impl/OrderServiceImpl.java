package com.elliot.github.olivers.service.impl;

import com.elliot.github.olivers.domain.Order;
import com.elliot.github.olivers.enums.OrderEventEnum;
import com.elliot.github.olivers.enums.OrderStatusEnum;
import com.elliot.github.olivers.service.OrderService;
import com.elliot.github.olivers.service.OrderStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderStatusService orderStatusService;

    @Override
    public void creatOrder() {

    }

    @Override
    public void payOrder() {
        Order order = new Order();
        order.setId(1);
        order.setAmount(new BigDecimal("90"));
        order.setOrderStatusEnum(OrderStatusEnum.CREATED);

        if (!orderStatusService.sendEvent(order, OrderEventEnum.PAY)) {
            throw new RuntimeException("订单状态流转异常");
        }
    }


}
