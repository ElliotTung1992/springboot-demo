package com.github.dge1992.mybatis.service;

import com.github.dge1992.mybatis.domain.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author 董感恩
 * @date 2020-02-09 21:28
 * @desc 用户服务接口
 */
public interface IUserService {

    Optional<User> getUserById(@NotNull Integer id);
}
