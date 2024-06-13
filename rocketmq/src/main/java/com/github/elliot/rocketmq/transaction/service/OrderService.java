package com.github.elliot.rocketmq.transaction.service;

import com.github.elliot.rocketmq.transaction.domain.OrderDTO;
import org.apache.rocketmq.client.exception.MQClientException;

public interface OrderService {

    void createOrder(OrderDTO orderDTO, String transactionId);

    void createOrder(OrderDTO order) throws MQClientException;
}
