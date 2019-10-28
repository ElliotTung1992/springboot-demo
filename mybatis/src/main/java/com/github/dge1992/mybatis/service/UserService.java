package com.github.dge1992.mybatis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.dge1992.mybatis.domain.User;
import com.github.dge1992.mybatis.mapper.UserMapper;
import com.github.dge1992.mybatis.utils.ConnectionUtil;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

    @Autowired
    private SqlSessionTemplate template;

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

    public void getCompanyList() {
        //userMapper.getCompanyList();
//        while (true){
            ConnectionUtil.run(template, " select * from user");
//        }
        //return "查询成功";
    }

    public Object updateById() {
        User user = new User();
        user.setId(1);
        user.setUserName("aaaa");
        user.setVersion(3);
        userMapper.updateById(user);
        return "更新成功";
    }

    @Transactional
    public Object testTransactional(User user) {
        userMapper.insert(user);
        //int i = 10/0;
        userMapper.insert(user);
        return "测试事务";
    }


}
