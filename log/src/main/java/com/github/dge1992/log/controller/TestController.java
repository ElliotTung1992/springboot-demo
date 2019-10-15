package com.github.dge1992.log.controller;

import lombok.extern.java.Log;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class TestController {

    @RequestMapping("/test")
    public void test(){
        MDC.put("TRACE_LOG_ID", "aaaaaaaaaaaaa");
        log.info("haha");
    }
}
