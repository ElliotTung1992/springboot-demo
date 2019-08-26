package com.github.dge1992.jwt.controller;

import com.github.dge1992.jwt.doamin.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/23
 **/
@RestController
public class TestController {

    @RequestMapping("/test")
    public ResponseEntity hello(@RequestBody User simpleObject) {
        System.out.println(simpleObject.getUser());
        return ResponseEntity.ok("请求成功!");
    }
}
