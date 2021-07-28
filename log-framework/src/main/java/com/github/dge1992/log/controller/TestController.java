package com.github.dge1992.log.controller;

import lombok.extern.java.Log;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 * @author dge
 * @date 2021-07-19 10:35
 */
@Log
@RestController
public class TestController {

    @RequestMapping("/test")
    public void test(){
        MDC.put("TRACE_LOG_ID", "aaaaaaaaaaaaa");
        log.info("haha");
    }
}
