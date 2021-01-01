package com.github.dge1992.restfuldemo.httpClient;

import com.alibaba.fastjson.JSONObject;
import com.github.dge1992.restfuldemo.domain.HttpResult;
import com.github.dge1992.restfuldemo.httpclient.HttpClientHandler;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 董感恩
 * @date 2020-03-05 15:48
 * @desc HttpClient测试
 */
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpClientTest {

    @Autowired
    HttpClientHandler httpClientHandler;

    /**
     * @author 董感恩
     * @date 2020-03-05 16:11:32
     * @desc application/x-www-form-urlencoded Post形式
     **/
    @Test
    public void doPostParam() throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("name", "dge");
        param.put("age", 11);
        param.put("id", 1);
        HttpResult httpResult = httpClientHandler.doPostForm("http://localhost:8090/doPostForm", param, null);
        log.info("doPostParam:" + httpResult);
    }

    /**
     * @author 董感恩
     * @date 2020-03-05 16:11:32
     * @desc application/json Post形式
     **/
    @Test
    public void doPostJson() throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("name", "dge");
        param.put("age", 11);
        param.put("id", 1);
        HttpResult httpResult = httpClientHandler.doPostJson("http://localhost:8090/doPostJson", param, null);
        log.info("doPostParam:" + httpResult);
    }
    
    /**
     * @author 董感恩
     * @date 2020-03-06 13:35:20
     * @desc Get请求无参数
     **/
    @Test
    public void doGet() throws Exception {
        HttpResult httpResult = httpClientHandler.doGet("http://localhost:8090/doGet");
        log.info("doGet:" + httpResult);
    }

    /**
     * @author 董感恩
     * @date 2020-03-06 13:55:13
     * @desc Get请求有参数
     **/
    @Test
    public void doGetParam() throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("name", "dge");
        param.put("age", 11);
        param.put("id", 1);
        HttpResult httpResult = httpClientHandler.doGet("http://localhost:8090/doGetParam", param);
        log.info("doGet:" + httpResult);
    }

    /**
     * @author 董感恩
     * @date 2020-03-06 13:56:24
     * @desc 测试重试
     **/
    @Test
    public void testRetry() throws Exception {
        httpClientHandler.doGet("http://localhost:8090/testRetry");
    }
}
