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
import java.util.List;

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

    //水平单库分表，插入数据
    @Test
    public void addCourse() {
        for (int i = 1; i <= 10; i++) {
            Course course = new Course();
            course.setCname("Java").setUserId(100L + i).setStatus("Normal" + i);
            courseMapper.insert(course);
        }
    }

    //水平单库分表, 查询
    @Test
    public void findCourse() {
        Course course = courseMapper.selectOne(new QueryWrapper<Course>().eq("cid", 497734210715385857l));
        System.out.println(course);

        Course course2 = courseMapper.selectOne(new QueryWrapper<Course>().eq("user_id", 101));
        System.out.println(course2);
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

    //水平单库分表, 查询
    @Test
    public void findCourseDB() {
        Course course = courseMapper.selectOne(new QueryWrapper<Course>().eq("cid", 497798262833545217l));
        System.out.println(course);

        List<Course> courseList = courseMapper.selectList(new QueryWrapper<Course>().eq("user_id", 101));
        courseList.stream().forEach(System.out::println);

        List<Course> courseList2 = courseMapper.selectList(new QueryWrapper<Course>().eq("cname", "JavaDB"));
        courseList2.stream().forEach(System.out::println);
    }

    //垂直分库 新增
    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("Mike").setAge(19);
        userMapper.insert(user);
    }

    //垂直分库 查询
    @Test
    public void selectUser() {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", "Mike"));
        System.out.println(user);
    }

    @Test
    public void addDict() {
        Dict dict = new Dict();
        dict.setDictName("测试").setDictValue("启用");
        dictMapper.insert(dict);
    }

}