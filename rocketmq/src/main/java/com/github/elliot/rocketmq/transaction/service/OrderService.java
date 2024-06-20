package com.github.elliot.rocketmq.transaction.service;

import com.github.elliot.rocketmq.transaction.dto.OrderDTO;
import org.apache.rocketmq.client.exception.MQClientException;

public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO orderDTO
     * @param transactionId transactionId
     */
    void createOrder(OrderDTO orderDTO, String transactionId);

    /**
     * 创建订单
     * @param order order
     * @throws MQClientException
     */
    void createOrder(OrderDTO order) throws MQClientException;
}
