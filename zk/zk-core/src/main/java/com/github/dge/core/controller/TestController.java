package com.github.dge.core.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dge
 * @version 1.0
 * @date 2022-01-04 3:57 PM
 */
@RestController
public class TestController {

    @Value("${name}")
    private String name;

    @GetMapping("/getValue")
    public String getValue(){
        return name;
    }
}
