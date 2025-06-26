package com.elliot.github.olivers.service.impl;

import com.elliot.github.olivers.domain.Order;
import com.elliot.github.olivers.enums.OrderEventEnum;
import com.elliot.github.olivers.enums.OrderStatusEnum;
import com.elliot.github.olivers.mapper.OrderMapper;
import com.elliot.github.olivers.service.OrderService;
import com.elliot.github.olivers.service.OrderStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Lazy
    @Autowired
    private OrderStatusService orderStatusService;

    @Transactional
    @Override
    public void creatOrder() {

    }

    @Override
    public void payOrder() {
        Order order = new Order();
        order.setId(1);
        order.setAmount(new BigDecimal("90"));
        order.setOrderStatus(OrderStatusEnum.CREATED.toString());

        if (!orderStatusService.sendEvent(order, OrderEventEnum.PAY)) {
            throw new RuntimeException("订单状态流转异常");
        }
    }

    @Override
    public Order queryOrder() {
        Order order = orderMapper.selectById(1);
        return order;
    }

    @Transactional
    @Override
    public void update() {
        Order order1 = orderMapper.selectById(1);
        order1.setOrderStatus("5");
        orderMapper.updateById(order1);
        int i = 10 / 0;
    }


}
