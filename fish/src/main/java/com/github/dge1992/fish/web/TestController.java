package com.github.dge1992.fish.web;

import com.github.dge1992.fish.spring.lifecycle.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-21 1:03 下午
 */
@RestController
public class TestController {

    @Autowired
    private Person person;

    @GetMapping("/testSpring")
    public void testSpring(){
        System.out.println(person);
    }
}
