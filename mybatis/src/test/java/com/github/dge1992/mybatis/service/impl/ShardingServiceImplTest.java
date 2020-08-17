package com.github.dge1992.mybatis.service.impl;

import com.github.dge1992.mybatis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author 董感恩
 * @date 2020-08-17 17:20
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingServiceImplTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testShardingProxy() {
        userMapper.insertShardingProxyOrder();
    }
}