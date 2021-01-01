package com.github.dge1992.trance.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 董感恩
 * @date 2020-09-07 10:24
 * @desc
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public void test(){
        System.out.println("haha");
    }
}
