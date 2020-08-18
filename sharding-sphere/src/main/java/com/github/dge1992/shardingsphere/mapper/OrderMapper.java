package com.github.dge1992.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.dge1992.shardingsphere.domain.Order;
import com.github.dge1992.shardingsphere.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 董感恩
 * @date 2020-07-17 16:59
 * @desc
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    void insertShardingProxyOrder(@Param("order") Order order);
}
