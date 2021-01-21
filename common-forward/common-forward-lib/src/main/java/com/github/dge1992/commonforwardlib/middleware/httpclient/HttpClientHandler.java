package com.github.dge1992.commonforwardlib.middleware.httpclient;

import com.alibaba.fastjson.JSON;
import com.github.dge1992.commonforwardapi.model.HttpForwardResult;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 董感恩
 * @date 2020-02-24 17:24
 * HttpClient处理器
 */
@Component
public class HttpClientHandler {

    private Logger logger = LoggerFactory.getLogger(HttpClientHandler.class);

    @Autowired
    private CloseableHttpClient httpClient;

    /**
     * 不带参数的Get请求
     *
     * @param url 请求地址
     * @return com.github.dge1992.commonforward.api.model.HttpForwardResult
     * @author dge
     * @date 2021-01-20 13:54
     */
    public HttpForwardResult doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
        // 发起请求
        logger.info("HttpClient对 " + url + " 发起Get请求！");
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        HttpForwardResult httpResult = buildResponse(response);
        logger.info(url + " 的返回结果是:" + JSON.toJSONString(httpResult));
        return httpResult;
    }

    /**
     * 带参数的Get请求
     *
     * @param url 请求地址
     * @param map 请求参数
     * @return com.github.dge1992.commonforward.api.model.HttpForwardResult
     * @author dge
     * @date 2021-01-20 13:54
     */
    public HttpForwardResult doGet(String url, Map<String, Object> map) throws Exception {
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
     * 带参数的Post请求
     *
     * @param url        请求地址
     * @param map        请求参数
     * @param headParams 请求头信息
     * @return com.github.dge1992.commonforward.api.model.HttpForwardResult
     * @author dge
     * @date 2021-01-20 13:55
     */
    public HttpForwardResult doPost(String url, Map<String, Object> map, Map<String, String> headParams) throws Exception {
        // 发起请求
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map == null) {
            return null;
        }
        List<NameValuePair> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        return doPost(url, list, headParams);
    }

    /**
     * 带参数的Post请求
     *
     * @param url        请求地址
     * @param list       请求参数
     * @param headParams 请求头信息
     * @return com.github.dge1992.commonforward.api.model.HttpForwardResult
     * @author dge
     * @date 2021-01-20 13:55
     */
    public HttpForwardResult doPost(String url, List<NameValuePair> list, Map<String, String> headParams) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 拼接请求头参数
        if (MapUtils.isNotEmpty(headParams)) {
            for (Map.Entry<String, String> entry : headParams.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // 构造from表单对象
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
        // 把表单放到post里
        httpPost.setEntity(urlEncodedFormEntity);
        logger.info("HttpClient对 " + url + " 发起Post请求！");
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        HttpForwardResult httpResult = buildResponse(response);
        logger.info(url + " 的返回结果是:" + JSON.toJSONString(httpResult));
        return httpResult;
    }

    /**
     * Json形式的Post请求
     *
     * @param url        请求地址
     * @param jsonStr    请求参数
     * @param headParams 请求头信息
     * @return com.github.dge1992.commonforward.api.model.HttpForwardResult
     * @author dge
     * @date 2021-01-20 13:56
     */
    public HttpForwardResult doPostJson(String url, String jsonStr, Map<String, String> headParams) throws IOException {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 拼接请求头参数
        if (MapUtils.isNotEmpty(headParams)) {
            for (Map.Entry<String, String> entry : headParams.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        httpPost.addHeader("Content-type", "application/json");
        // 发起请求
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (StringUtils.isNotBlank(jsonStr)) {
            StringEntity formEntity = new StringEntity(jsonStr, "UTF-8");
            httpPost.setEntity(formEntity);
        }
        logger.info("HttpClient对 " + url + " 发起Post请求！");
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        HttpForwardResult httpResult = buildResponse(response);
        logger.info(url + " 的返回结果是:" + JSON.toJSONString(httpResult));
        return httpResult;
    }

    /**
     * 构建返回值
     *
     * @param response 响应
     * @return com.github.dge1992.commonforward.api.model.HttpForwardResult
     * @author dge
     * @date 2021-01-20 13:57
     */
    private HttpForwardResult buildResponse(CloseableHttpResponse response) throws IOException {
        return new HttpForwardResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }
}
