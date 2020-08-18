package com.github.dge1992.shardingsphere.service.impl;

import com.github.dge1992.shardingsphere.domain.Order;
import com.github.dge1992.shardingsphere.mapper.OrderMapper;
import com.github.dge1992.shardingsphere.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author 董感恩
 * @date 2020-08-18 11:03
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingSphereProxyServiceImplTest {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * @author 董感恩
     * @date 2020-08-18 11:34:08
     * @desc 单库分表
     **/
    @Test
    public void testShardingProxy() {
        Order order = new Order();
        order.setUserId(4);
        order.setOrderId(4);
        orderMapper.insertShardingProxyOrder(order);
    }
}