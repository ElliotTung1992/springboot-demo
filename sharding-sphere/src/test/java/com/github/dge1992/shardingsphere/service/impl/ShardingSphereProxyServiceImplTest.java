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
     * @desc 分表
     **/
    @Test
    public void testShardingProxy() {
        Order order = new Order();
        order.setUserId(4);
        order.setOrderId(4);
        orderMapper.insertShardingProxyOrder(order);
    }

    /**
     * @author 董感恩
     * @date 2020-08-18 11:34:08
     * @desc 分库分表
     **/
    @Test
    public void testShardingDBAndTable() {
        Order order = new Order();
        order.setUserId(1);
        order.setOrderId(1);
        orderMapper.insertShardingProxyOrder(order);

        Order order2 = new Order();
        order2.setUserId(2);
        order2.setOrderId(2);
        orderMapper.insertShardingProxyOrder(order2);
    }
}