package com.github.dge1992.shardingsphere.service.impl;

import com.github.dge1992.shardingsphere.mapper.OrderMapper;
import com.github.dge1992.shardingsphere.service.IShardingSphereProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 董感恩
 * @date 2020-08-18 10:56
 * @desc
 */
@Service
public class ShardingSphereProxyServiceImpl implements IShardingSphereProxyService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void insertShardingProxyOrder() {
        orderMapper.insertShardingProxyOrder(null);
    }
}
