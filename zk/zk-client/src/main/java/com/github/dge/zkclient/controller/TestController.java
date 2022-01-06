package com.github.dge.zkclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dge
 * @version 1.0
 * @date 2022-01-06 10:22 AM
 */
@RestController
public class TestController {

    @Value("${name}")
    private String name;

    @GetMapping("/test")
    public String test(){
        return name;
    }
}
