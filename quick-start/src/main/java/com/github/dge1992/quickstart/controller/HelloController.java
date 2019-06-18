package com.github.dge1992.quickstart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/6/18
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
