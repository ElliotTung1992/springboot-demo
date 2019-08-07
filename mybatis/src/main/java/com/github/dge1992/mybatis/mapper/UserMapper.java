package com.github.dge1992.mybatis.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.dge1992.mybatis.domain.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> selectUserList();
}
