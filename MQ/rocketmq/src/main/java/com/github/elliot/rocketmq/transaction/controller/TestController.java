package com.github.elliot.rocketmq.transaction.controller;


import com.github.elliot.rocketmq.transaction.domain.Order;
import com.github.elliot.rocketmq.transaction.mapper.OrderMapper;
import com.github.elliot.rocketmq.transaction.mapper.TransactionLogMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class TestController {

    @Autowired
    private TransactionLogMapper transactionLogMapper;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping("/test")
    public void test(){
        Order order = new Order();
        order.setId(1L);
        order.setOrderNo("123");
        order.setCommodityCode("CommodityCode");
        orderMapper.insert(order);
    }
}
