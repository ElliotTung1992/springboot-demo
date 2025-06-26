package com.elliot.github.olivers.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elliot.github.olivers.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
