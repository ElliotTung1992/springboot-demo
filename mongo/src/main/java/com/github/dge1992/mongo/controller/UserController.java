package com.github.dge1992.mongo.controller;

import com.github.dge1992.mongo.dao.UserDao;
import com.github.dge1992.mongo.doamin.User;
import com.github.dge1992.mongo.doamin.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author dongganene
 * @Description
 * @Date 2019/6/13
 **/
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @PostMapping("/insert")
    public Object insert(@RequestBody User user){
        userDao.insert(user);
        return "success";
    }

    @PostMapping("/findByNameAndAge")
    public Object findByNameAndAge(@RequestBody User user){
        User user2 = userDao.findByNameAndAge(user.getName(), user.getAge());
        return user2;
    }

    @PostMapping("/findByNameOrAge")
    public Object findByNameOrAge(@RequestBody User user){
        List<User> users = userDao.findByNameOrAge(user.getName(), user.getAge());
        return users;
    }

    @PostMapping("/findByAge")
    public Object findByAge(@RequestBody User user){
        List<User> users = userDao.findByAgeIs(user.getAge());
        return users;
    }

    @PostMapping("/findByAgeBetween")
    public Object findByAgeBetween(@RequestBody UserParam userParam){
        List<User> users = userDao.findByAgeBetween(userParam.getStartAge(), userParam.getEndAge());
        return users;
    }

    @PostMapping("/findByAgeLessThan")
    public Object findByAgeLessThan(@RequestBody UserParam userParam){
        List<User> users = userDao.findByAgeLessThan(userParam.getAge());
        return users;
    }

    @PostMapping("/findByAgeLessThanEqual")
    public Object findByAgeLessThanEqual(@RequestBody UserParam userParam){
        List<User> users = userDao.findByAgeLessThanEqual(userParam.getAge());
        return users;
    }

    @PostMapping("/findByAgeGreaterThan")
    public Object findByAgeGreaterThan(@RequestBody UserParam userParam){
        List<User> users = userDao.findByAgeGreaterThan(userParam.getAge());
        return users;
    }

    @PostMapping("/findByAgeGreaterThanEqual")
    public Object findByAgeGreaterThanEqual(@RequestBody UserParam userParam){
        List<User> users = userDao.findByAgeGreaterThanEqual(userParam.getAge());
        return users;
    }

    @PostMapping("/findByAgeAfter")
    public Object findByAgeAfter(@RequestBody UserParam userParam){
        List<User> users = userDao.findByAgeAfter(userParam.getAge());
        return users;
    }

    @PostMapping("/findByAgeBefore")
    public Object findByAgeBefore(@RequestBody UserParam userParam){
        List<User> users = userDao.findByAgeBefore(userParam.getAge());
        return users;
    }

    @PostMapping("/findByAgeIsNull")
    public Object findByAgeIsNull(){
        List<User> users = userDao.findByAgeIsNull();
        return users;
    }

    @PostMapping("/findByAgeIsNotNull")
    public Object findByAgeIsNotNull(){
        List<User> users = userDao.findByAgeIsNotNull();
        return users;
    }

    @PostMapping("/findByNameLike")
    public Object findByNameLike(@RequestBody UserParam userParam){
        List<User> users = userDao.findByNameLike(userParam.getName());
        return users;
    }

    @PostMapping("/findByNameNotLike")
    public Object findByNameNotLike(@RequestBody UserParam userParam){
        List<User> users = userDao.findByNameNotLike(userParam.getName());
        return users;
    }

    @PostMapping("/findByNameStartingWith")
    public Object findByNameStartingWith(@RequestBody UserParam userParam){
        List<User> users = userDao.findByNameStartingWith(userParam.getName());
        return users;
    }

    @PostMapping("/findByNameEndingWith")
    public Object findByNameEndingWith(@RequestBody UserParam userParam){
        List<User> users = userDao.findByNameEndingWith(userParam.getName());
        return users;
    }

    @PostMapping("/findByNameContaining")
    public Object findByNameContaining(@RequestBody UserParam userParam){
        List<User> users = userDao.findByNameContaining(userParam.getName());
        return users;
    }

    @PostMapping("/findByNameOrderByAgeDesc")
    public Object findByNameOrderByAgeDesc(@RequestBody UserParam userParam){
        List<User> users = userDao.findByNameOrderByAgeDesc(userParam.getName());
        return users;
    }

    @PostMapping("/findByNameOrderByAgeAsc")
    public Object findByNameOrderByAgeAsc(@RequestBody UserParam userParam){
        List<User> users = userDao.findByNameOrderByAgeAsc(userParam.getName());
        return users;
    }

    @PostMapping("/findByNameNot")
    public Object findByNameNot(@RequestBody UserParam userParam){
        List<User> users = userDao.findByNameNot(userParam.getName());
        return users;
    }

    @PostMapping("/findByAgeIn")
    public Object findByAgeIn(){
        List<User> users = userDao.findByAgeIn(new Integer[]{13,14,15});
        return users;
    }

    @PostMapping("/findByAgeNotIn")
    public Object findByAgeNotIn(){
        List<User> users = userDao.findByAgeNotIn(new Integer[]{13,14,15});
        return users;
    }
}
