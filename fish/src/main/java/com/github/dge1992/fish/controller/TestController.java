package com.github.dge1992.fish.controller;

import com.github.dge1992.fish.spring.aop.DistributedLock;
import com.github.dge1992.fish.spring.lifecycle.bean.PersonBean;
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
    private PersonBean personBean;

    // @DistributedLock(lockKey = "#{@personBean.setName(#name)}")
    @DistributedLock(lockKey = "#{#name}")
    @GetMapping("/testSpring")
    public void testSpring(String name){
        System.out.println("aa" + personBean.getName());
    }

    @PostMapping("/save")
    public void savePerson(@RequestBody PersonBean personBean){
        System.out.println(personBean);
    }
}
