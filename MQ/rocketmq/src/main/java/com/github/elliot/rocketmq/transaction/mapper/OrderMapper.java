package com.github.elliot.rocketmq.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.elliot.rocketmq.transaction.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    void createOrder(Order order);
}
