package com.github.elliot.rocketmq2.transaction.service;

import com.github.elliot.rocketmq2.transaction.DTO.OrderDTO;

public interface PointsService {
    void increasePoints(OrderDTO order);
}
