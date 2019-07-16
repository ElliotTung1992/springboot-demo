package com.github.dge1992.mybatis.controller;

import com.github.dge1992.mybatis.domain.User;
import com.github.dge1992.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/15
 **/
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/findByName")
    public Object findByName(){
        return userMapper.findByName("aaa");
    }

    @RequestMapping("/insert")
    public Object insert(){
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("name", "linjiajia");
//        map.put("age", 15);
//        userMapper.insertByMap(map);
//        return userMapper.insert("yaobaofeng", 10);
        User user = new User();
        user.setName("lidihui");
        user.setAge(12);
        return userMapper.insertByUser(user);
    }
}
