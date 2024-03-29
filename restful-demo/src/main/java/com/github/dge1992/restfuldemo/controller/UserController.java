package com.github.dge1992.restfuldemo.controller;

import com.github.dge1992.restfuldemo.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/6/24
 **/
@Api(description = "RESTFul-API测试", tags = {"UserController"})
@RequestMapping("/user")
@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    Map<Long, User> map = new ConcurrentHashMap<>();

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping
    public Object addUser(@RequestBody User user){
        user.setAge(user.getAge() + 10);
        map.put(user.getId(), user);
        return "添加成功";
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @GetMapping
    public List<User> getUserList(){
        logger.info("getUserList开始调用");
        List<User> users = returnUserList();
        logger.info("getUserList结束调用");
        return users;
    }

    @ApiOperation(value = "获取用户详情", notes = "获取用户详情")
    @GetMapping("/{id}")
    public User getUser(@ApiParam(value = "主键编号", required = true) @PathVariable long id){
        return map.get(id);
    }

    @ApiOperation(value = "修改用户", notes = "修改用户")
    @PutMapping
    public String editUser(@ModelAttribute User user){
        map.put(user.getId(), user);
        return "修改成功";
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @DeleteMapping()
    public String deleteUser(@ApiParam(value = "主键编号", required = true) @RequestParam("id") long id){
        map.remove(id);
        return "删除成功";
    }

    /**
     * 构建UserList
     * @return java.util.List<com.github.dge1992.restfuldemo.domain.User>
     * @author dge
     * @date 2021-03-08 17:16
     */
    private List<User> returnUserList(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setId(1L);
        user.setName("dge");
        user.setAge(11);
        User user2 = new User();
        user2.setId(2L);
        user2.setName("fnn");
        user2.setAge(12);
        map.put(1L, user);
        map.put(2L, user2);
        return new ArrayList<>(map.values());
    }
}
