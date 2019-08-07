package com.github.dge1992.mybatis.service;

import com.github.dge1992.mybatis.domain.User;
import com.github.dge1992.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/7
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> selectUserList() {
        return userMapper.selectUserList();
    }
}
