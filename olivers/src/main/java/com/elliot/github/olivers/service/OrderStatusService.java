package com.elliot.github.olivers.service;

import com.elliot.github.olivers.domain.Order;
import com.elliot.github.olivers.enums.OrderEventEnum;

/**
 * OrderStatusService
 */
public interface OrderStatusService {

    /**
     * sendEvent
     * @param order order
     * @param orderEventEnum orderEventEnum
     */
    boolean sendEvent(Order order, OrderEventEnum orderEventEnum);
}
