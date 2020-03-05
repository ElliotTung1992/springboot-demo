package com.github.dge1992.mybatis.controller;

import com.github.dge1992.common.domain.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.io.InterruptedIOException;

/**
 * @author 董感恩
 * @date 2020-02-28 17:43
 * @desc
 */
@Log4j2
@RestController
public class HttpClientController {

    /**
     * @author 董感恩
     * @date 2020-02-28 17:44:13
     * @desc 测试Get请求无返回值
     **/
    @GetMapping("/testGetNoResult")
    public Object testGetNoResult(){
        int i = 1 / 0;
        return "success";
    }

    /**
     * @author 董感恩
     * @date 2020-02-28 17:44:13
     * @desc 测试Get请求无返回值
     **/
    @GetMapping("/testGetParams")
    public Object testGetParams(@RequestParam String name){
        return name;
    }

    /**
     * @author 董感恩
     * @date 2020-02-28 19:51:55
     * @desc 测试Post请求
     **/
    @PostMapping("/testPostResult")
    public Object testPostResult(){
        //int i = 10 / 0;
        User user = new User();
        user.setId(1);
        user.setAge(11);
        user.setName("dge");
        return user;
    }

    /**
     * @author 董感恩
     * @date 2020-02-28 17:44:13
     * @desc 测试Get请求无返回值
     **/
    @PostMapping("/testPostParams")
    public Object testPostParams(@RequestParam("name") String name){
        return name;
    }

    /**
     * @author 董感恩
     * @date 2020-02-29 18:30:27
     * @desc 测试Post请求
     **/
    @PostMapping("/testPostJson")
    public Object testPostJson(@RequestBody User user){
        return user;
    }

    /**
     * @author 董感恩
     * @date 2020-03-01 18:26:40
     * @desc 测试重试
     **/
    @GetMapping("/testRetry")
    public Object testRetry() {
        log.info("testRetry被调用！");
        try {
            Thread.sleep(100000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "测试重试";
    }
}
