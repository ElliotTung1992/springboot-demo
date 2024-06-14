package com.github.elliot.rocketmq2.transaction.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.elliot.rocketmq2.transaction.DTO.OrderDTO;
import com.github.elliot.rocketmq2.transaction.domain.Points;
import com.github.elliot.rocketmq2.transaction.mapper.PointsMapper;
import com.github.elliot.rocketmq2.transaction.service.PointsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PointsServiceImpl implements PointsService {

    @Autowired
    PointsMapper pointsMapper;

    Snowflake snowflake = new Snowflake(1,1);

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void increasePoints(OrderDTO order) {

        //入库之前先查询，实现幂等
        LambdaQueryWrapper<Points> pointsQueryWrapper = Wrappers.<Points>lambdaQuery()
                        .eq(Points::getOrderNo, order.getOrderNo());

        Points points = pointsMapper.selectOne(pointsQueryWrapper);
        if (Objects.nonNull(points)){
            logger.info("积分添加完成，订单已处理。{}",order.getOrderNo());
        }else{
            points = new Points();
            points.setId(snowflake.nextId());
            points.setUserId(order.getUserId());
            points.setOrderNo(order.getOrderNo());
            Double amount = order.getAmount();
            points.setPoints(amount.intValue() * 10);
            points.setRemarks("商品消费共【"+order.getAmount()+"】元，获得积分"+points.getPoints());
            pointsMapper.insert(points);
            logger.info("已为订单号码{}增加积分。",points.getOrderNo());
        }
    }
}
