package com.github.dge1992.mybatis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.dge1992.mybatis.domain.User;
import com.github.dge1992.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/selectList")
    public Page<Map<String, Object>> selectList(){
        Page<User> page = new Page<>(1, 10);
        return userService.selectList(page);
    }

    @GetMapping("/deleteById/{id}")
    public Object deleteById(@PathVariable Integer id){
        userService.deleteById(id);
        return "删除成功";
    }

    @GetMapping("/getList")
    public List<User> getList(){
        return userService.getList();
    }

}
