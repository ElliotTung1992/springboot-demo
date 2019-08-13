package com.github.dge1992.mybatis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.dge1992.mybatis.domain.User;
import com.github.dge1992.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public User selectUserById(Integer id) {
        return userMapper.selectById(id);
    }

    public Page<Map<String, Object>> selectList(Page<User> page) {
        return userMapper.selectListUser(page);
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    public List<User> getList() {
        return userMapper.selectList(null);
    }

    public Object insertUser(User user) {
        return userMapper.insert(user);
    }

    public Object updateAll() {
        userMapper.updateAll();
        return "全部更新";
    }

    public Object getCompanyList() {
        userMapper.getCompanyList();
        return "查询成功";
    }
}
