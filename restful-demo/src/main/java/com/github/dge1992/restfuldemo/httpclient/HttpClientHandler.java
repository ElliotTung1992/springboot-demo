package com.github.dge1992.restfuldemo.httpclient;

import com.alibaba.fastjson.JSON;
import com.github.dge1992.restfuldemo.domain.HttpResult;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.MapUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 董感恩
 * @date 2020-02-24 17:24
 * @desc HttpClient处理器
 */
@Log4j2
@Component
public class HttpClientHandler {

    @Autowired
    private CloseableHttpClient httpClient;
    
    /**
     * @author 董感恩
     * @date 2020-02-28 17:34:05
     * @desc 不带参数的Get请求
     *
     * @return*/
    public HttpResult doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
        // 发起请求
        log.info("HttpClient对 " + url + " 发起Get请求！");
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        HttpResult httpResult = buildResponse(response);
        log.info(url + " 的返回结果是:" + JSON.toJSONString(httpResult));
        return httpResult;
    }

    /**
     * @author 董感恩
     * @date 2020-02-28 17:48:56
     * @desc 带参数的Get请求
     **/
    public HttpResult doGet(String url, Map<String, Object> map) throws Exception {
        // 构建请求
        URIBuilder uriBuilder = new URIBuilder(url);
        // 遍历map,拼接请求参数
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());
    }

    /**
     *
     *  UrlEncodedFormEntity 和 StringEntity的区别
     *  后台接收参数的形式不一样
     *  UrlEncodedFormEntity是a=value1&b=value2
     *  StringEntity是{"a":"value1", "b":"value2"}的形式
     *
     **/

    /**
     * @author 董感恩
     * @date 2020-02-28 19:43:36
     * @desc 带参数的Post请求
     *       a=value1&b=value2
     **/
    public HttpResult doPostForm(String url, Map<String, Object> map, Map<String, String> headParams) throws Exception {
        HttpPost httpPost = doPost(url, headParams);
        //提交的数据按照 key1=val1&key2=val2 的方式进行编码
        httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map != null) {
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }
        HttpResult httpResult = sendPost(httpPost, url);
        return httpResult;
    }

    /**
     * @author 董感恩
     * @date 2020-02-29 21:15:34
     * @desc Json形式的Post请求
     *       {"a":"value1", "b":"value2"}
     **/
    public HttpResult doPostJson(String url, Map<String, Object> map, Map<String, String> headParams) throws IOException {
        HttpPost httpPost = doPost(url, headParams);
        //以json形式传递数据
        httpPost.addHeader("Content-type", "application/json");
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map != null) {
            StringEntity formEntity = new StringEntity(JSON.toJSONString(map), "UTF-8");
            httpPost.setEntity(formEntity);
        }
        HttpResult httpResult = sendPost(httpPost, url);
        return httpResult;
    }

    /**
     * @author 董感恩
     * @date 2020-03-05 15:26:42
     * @desc 创建Post请求，并设置头信息
     **/
    private HttpPost doPost(String url, Map<String, String> headParams){
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 拼接请求头参数
        if(MapUtils.isNotEmpty(headParams)){
            for (Map.Entry<String, String> entry : headParams.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return httpPost;
    }

    /**
     * @author 董感恩
     * @date 2020-03-05 15:27:24
     * @desc 发送Post请求并且记录日志
     **/
    private HttpResult sendPost(HttpPost httpPost, String url) throws IOException {
        log.info("HttpClient对 " + url + " 发起Post请求！");
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        HttpResult httpResult = buildResponse(response);
        log.info(url + " 的返回结果是:" + JSON.toJSONString(httpResult));
        return httpResult;
    }

    /**
     * @author 董感恩
     * @date 2020-02-28 20:04:48
     * @desc 构建返回值
     **/
    private HttpResult buildResponse(CloseableHttpResponse response) throws IOException {
        if(response.getStatusLine().getStatusCode() == 200){
            return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                    response.getEntity(), "UTF-8"));
        }
        return JSON.parseObject(EntityUtils.toString(
                response.getEntity(), "UTF-8"), HttpResult.class);
    }
}
