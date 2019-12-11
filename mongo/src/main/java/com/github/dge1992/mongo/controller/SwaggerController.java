package com.github.dge1992.mongo.controller;

import com.github.dge1992.mongo.doamin.CarParam;
import com.github.dge1992.mongo.doamin.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/6/21
 **/
@Api(description = "swagger测试", tags = {"SwaggerController"})
@RequestMapping("/swagger")
@RestController
public class SwaggerController {

    @GetMapping("/test")
    @ApiOperation(value = "测试对象接收参数", notes = "测试对象接收参数")
    public CarParam test(CarParam car){
        return car;
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "测试接收单个参数", notes = "测试接收单个参数")
    public User get(@ApiParam(value = "主键编号", required = true) @PathVariable("id") String id){
        User user = new User();
        user.setId(id);
        return user;
    }

    @PostMapping("insert")
    @ApiOperation(value = "测试对象接收参数", notes = "测试对象接收参数")
    public CarParam insert(@RequestBody CarParam car){
        return car;
    }
}
