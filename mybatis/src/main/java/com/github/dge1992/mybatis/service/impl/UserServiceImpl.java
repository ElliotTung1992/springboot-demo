package com.github.dge1992.mybatis.service.impl;

import com.github.dge1992.mybatis.domain.User;
import com.github.dge1992.mybatis.mapper.UserMapper;
import com.github.dge1992.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author 董感恩
 * @date 2020-02-09 21:29
 * @desc 用户服务实现
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<User> getUserById(Integer id) {
        return Optional.ofNullable(userMapper.selectById(id));
    }
}
