package com.elliot.github.olivers.mapper;

import org.springframework.stereotype.Component;

@Component
public interface OrderMapper {

    void updateOrderStatus(Integer orderId);
}
