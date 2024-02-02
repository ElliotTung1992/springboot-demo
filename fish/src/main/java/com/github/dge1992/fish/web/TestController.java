package com.github.dge1992.fish.web;

import com.github.dge1992.fish.spring.aop.DistributedLock;
import com.github.dge1992.fish.spring.lifecycle.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-21 1:03 下午
 */
@PropertySource(value="classpath:application.yml",encoding = "utf-8")
@RestController
public class TestController {

    @Value("${oa.leave.agent}")
    private String agent;

    @Value("${oa.leave.time}")
    private List<String> timeList;

    @Autowired
    private Person person;

    @DistributedLock(lockKey = "")
    @GetMapping("/testSpring")
    public void testSpring(){
        System.out.println(agent);
        System.out.println(person);
        System.out.println(timeList);
    }

    @PostMapping("/save")
    public void savePerson(@RequestBody Person person){
        System.out.println(person);
    }
}
