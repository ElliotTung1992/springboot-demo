package com.github.dge1992.restfuldemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 董感恩
 * @date 2020-08-19 11:43
 * @desc
 */
@Api(description = "RESTFul-API测试", tags = {"SysController"})
@RequestMapping("/sys")
@RestController
public class SysController {

    @ApiOperation(value = "新增系统配置", notes = "新增系统配置")
    @PostMapping
    public Object addSys(){
        return null;
    }
}
