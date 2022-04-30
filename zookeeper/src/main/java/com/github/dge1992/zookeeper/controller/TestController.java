package com.github.dge1992.zookeeper.controller;

import com.github.dge1992.zookeeper.config.SubConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-08 9:47 AM
 */
@RestController
public class TestController {

    @Autowired
    private SubConfiguration subConfiguration;

    @GetMapping("/testSub")
    public void testSub(){
        System.out.println(subConfiguration.getName());
    }
}
