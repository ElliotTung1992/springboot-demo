package com.github.elliot.rocketmq2.transaction.controller;

import com.github.elliot.rocketmq2.transaction.domain.Points;
import com.github.elliot.rocketmq2.transaction.mapper.PointsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private PointsMapper pointsMapper;

    @GetMapping("/test")
    public void test(){
        Points points = new Points();
        points.setUserId(1L);
        points.setOrderNo("aaa");
        points.setPoints(11);
        points.setRemarks("remarks");
        pointsMapper.insert(points);
    }
}
