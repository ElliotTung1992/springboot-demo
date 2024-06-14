package com.github.elliot.rocketmq2.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.elliot.rocketmq2.transaction.domain.Points;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointsMapper extends BaseMapper<Points> {

}
