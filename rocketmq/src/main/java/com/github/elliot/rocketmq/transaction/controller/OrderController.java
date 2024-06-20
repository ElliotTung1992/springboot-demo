package com.github.elliot.rocketmq.transaction.controller;

import com.github.elliot.rocketmq.transaction.dto.OrderDTO;
import com.github.elliot.rocketmq.transaction.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create_order")
    public void createOrder(@RequestBody OrderDTO order) throws MQClientException {
        log.info("接收到订单数据：{}", order.getCommodityCode());
        orderService.createOrder(order);
    }
}
