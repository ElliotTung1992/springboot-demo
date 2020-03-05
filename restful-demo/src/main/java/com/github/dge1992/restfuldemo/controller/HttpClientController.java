package com.github.dge1992.restfuldemo.controller;

import com.github.dge1992.restfuldemo.domain.HttpResult;
import com.github.dge1992.restfuldemo.httpclient.HttpClientHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 董感恩
 * @date 2020-02-24 17:23
 * @desc
 */
@RestController
public class HttpClientController {

    @Autowired
    private HttpClientHandler httpClientHandler;

    @GetMapping("/testGet")
    public Object testGet() throws Exception {
        long start = System.currentTimeMillis();
        HttpResult httpResult = httpClientHandler.doGet("http://localhost:8090/selectUserList");
        System.out.println(System.currentTimeMillis() - start);
        return httpResult;
    }

    @GetMapping("/testGetNoResult")
    public Object testGetNoResult() throws Exception {
        HttpResult httpResult = httpClientHandler.doGet("http://localhost:8090/testGetNoResult");
        return httpResult;
    }

    @GetMapping("/testGetParams")
    public Object testGetParams() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "dge");
        HttpResult httpResult = httpClientHandler.doGet("http://localhost:8090/testGetParams", map);
        return httpResult;
    }

    @PostMapping("/testPostParams")
    public Object testPostParams() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "dge");
        HttpResult httpResult = httpClientHandler.doPost("http://localhost:8090/testPostParams", map, null);
        return httpResult;
    }

    @PostMapping("/testPostJson")
    public Object testPostJson() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "dge");
        Map<String, String> headParams = new HashMap<>();
        HttpResult httpResult = httpClientHandler.doPostJson("http://localhost:8090/testPostJson", map, headParams);
        return httpResult;
    }

    @GetMapping("/testRetry")
    public Object testRetry() throws Exception {
        httpClientHandler.doGet("http://localhost:8090/testRetry");
        return "测试重试！";
    }
}
