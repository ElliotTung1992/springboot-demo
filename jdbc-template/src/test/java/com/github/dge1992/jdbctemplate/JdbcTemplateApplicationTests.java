package com.github.dge1992.jdbctemplate;

import com.github.dge1992.jdbctemplate.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTemplateApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        // 准备，清空user表
        userService.deleteAllUsers();
    }

    @Test
    public void test() {
        // 插入5个用户
        userService.create("a", 1);
        userService.create("b", 2);
        userService.create("c", 3);
        userService.create("d", 4);
        userService.create("e", 5);

        List<Map<String, Object>> list = userService.selectUserList();
        list.stream().forEach(System.out::print);

        // 查数据库，应该有5个用户
        Assert.assertEquals(5, userService.getAllUsers().intValue());

        // 删除两个用户
        userService.deleteByName("a");
        userService.deleteByName("e");

        // 查数据库，应该有5个用户
        Assert.assertEquals(3, userService.getAllUsers().intValue());
    }

}
