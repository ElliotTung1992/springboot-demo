package com.github.dge1992.mybatis.controller;

import com.github.dge1992.common.domain.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

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
     * @desc 测试doPostForm
     **/
    @PostMapping("/doPostForm")
    public Object doPostForm(@RequestParam("name")String name, @RequestParam("age")Integer age, @RequestParam("id")Integer id) {
        return "name:" + name + " age:" + age + " id:" + id;
    }

    /**
     * @author 董感恩
     * @date 2020-02-29 18:30:27
     * @desc 测试doPostJson
     **/
    @PostMapping("/doPostJson")
    public Object doPostJson(@RequestBody User user){
        return user;
    }

    /**
     * @author 董感恩
     * @date 2020-03-06 13:39:05
     * @desc 测试doGet无参数
     **/
    @GetMapping("/doGet")
    public Object doGet(){
        return "doGet无参数成功";
    }

    /**
     * @author 董感恩
     * @date 2020-03-06 13:48:39
     * @desc 测试doGetParam
     **/
    @GetMapping("/doGetParam")
    public Object doGetParam(@RequestParam("id")Integer id, @RequestParam("name")String name, @RequestParam("age")Integer age){
        return "id:" + id + " name:" + name + "age:" + age;
    }

    /**
     * @author 董感恩
     * @date 2020-03-06 13:57:31
     * @desc 测试重试
     **/
    @GetMapping("/testRetry")
    public Object testRetry() throws InterruptedException {
        Thread.sleep(30000l);
        return "测试成功";
    }
}
