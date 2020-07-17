package com.github.dge1992.shardingsphere.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.dge1992.shardingsphere.domain.Course;
import com.github.dge1992.shardingsphere.domain.Dict;
import com.github.dge1992.shardingsphere.domain.User;
import com.github.dge1992.shardingsphere.mapper.CourseMapper;
import com.github.dge1992.shardingsphere.mapper.DictMapper;
import com.github.dge1992.shardingsphere.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 董感恩
 * @date 2020-07-17 15:01
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceImplTest {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private DictMapper dictMapper;

    @Test
    public void addCourse() {
        for (int i = 1; i <= 10; i++) {
            Course course = new Course();
            course.setCname("Java").setUserId(100L + i).setStatus("Normal" + i);
            courseMapper.insert(course);
        }
    }

    //水平单库分表
    @Test
    public void findCourse() {
        Course course = courseMapper.selectOne(new QueryWrapper<Course>().eq("cid", 490901825906540545l));
        System.out.println(course);
    }

    //水平分库分表
    @Test
    public void addCourseDb() {
        for (int i = 1; i <= 10; i++) {
            Course course = new Course();
            course.setCname("JavaDB").setUserId(100L).setStatus("Normal");
            courseMapper.insert(course);
        }
        for (int i = 1; i <= 10; i++) {
            Course course = new Course();
            course.setCname("JavaDB").setUserId(101L).setStatus("Normal");
            courseMapper.insert(course);
        }
    }

    //垂直分库
    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("Manaphy").setAge(18);
        userMapper.insert(user);
    }

    @Test
    public void addDict() {
        Dict dict = new Dict();
        dict.setDictName("测试").setDictValue("启用");
        dictMapper.insert(dict);
    }

}