package com.github.dge1992.mybatis.controller;

import com.github.dge1992.mybatis.domain.User;
import com.github.dge1992.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/7
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/selectUserList")
    public List<User> selectUserList(){
        return userService.selectUserList();
    }

    @GetMapping("/selectUserById/{id}")
    public User selectUserById(@PathVariable Integer id){
        return userService.selectUserById(id);
    }

}
