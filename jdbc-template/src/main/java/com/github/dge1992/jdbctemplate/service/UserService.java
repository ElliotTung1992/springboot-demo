package com.github.dge1992.jdbctemplate.service;

import java.util.List;
import java.util.Map;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/6/26
 **/
public interface UserService {

    /**
     * 新增一个用户
     * @param name
     * @param age
     */
    void create(String name, Integer age);

    /**
     * 根据name删除一个用户高
     * @param name
     */
    void deleteByName(String name);

    /**
     * 获取用户总量
     */
    Integer getAllUsers();

    /**
     * 删除所有用户
     */
    void deleteAllUsers();

    /**
     * @author dongganen
     * @date 2019/6/26
     * @desc: 查询用户列表
     */
    List<Map<String, Object>> selectUserList();
}
