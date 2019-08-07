package com.github.dge1992.mybatis.mapper;

import com.github.dge1992.mybatis.domain.User;

import java.util.List;

public interface UserMapper {

    List<User> selectUserList();
}
