package com.github.dge1992.restfuldemo.httpclient;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * @author 董感恩
 * @date 2020-02-24 17:00
 * @desc HttpClient配置类
 */
@Configuration
public class HttpClientConfig {

    @Value("${http.maxTotal}")
    private Integer maxTotal;

    @Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute;

    @Value("${http.connectTimeout}")
    private Integer connectTimeout;

    @Value("${http.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;

    @Value("${http.socketTimeout}")
    private Integer socketTimeout;

    @Value("${http.staleConnectionCheckEnabled}")
    private boolean staleConnectionCheckEnabled;

    /**
     * 连接池管理器，设置最大连接数、并发连接数
     *
     * @return
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        //最大连接数
        httpClientConnectionManager.setMaxTotal(maxTotal);
        //并发数
        httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return httpClientConnectionManager;
    }

    /**
     * 实例化连接池，设置连接池管理器。
     * 这里需要以参数形式注入上面实例化的连接池管理器
     *
     * @param httpClientConnectionManager
     * @return
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager,
                                                  RequestConfig requestConfig) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //设置连接池
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        //设置超时时间
        httpClientBuilder.setDefaultRequestConfig(requestConfig);
        //定义连接管理器将由多个客户端实例共享。如果连接管理器是共享的，则其生命周期应由调用者管理，如果客户端关闭则不会关闭。
        httpClientBuilder.setConnectionManagerShared(true);
        //启动线程，5秒钟清理一次线程
        new IdleConnectionThread(httpClientConnectionManager).start();
        //禁止重试，并发请求量大的情况推荐关闭
        //DefaultHttpRequestRetryHandler默认实现
        //httpClientBuilder.disableAutomaticRetries();
        //设置重试
        httpClientBuilder.setRetryHandler(new MyHttpRequestRetryHandler());
        return httpClientBuilder;
    }

    /**
     * 注入连接池，用于获取httpClient
     *
     * @param httpClientBuilder
     * @return
     */
    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }

    /**
     * Builder是RequestConfig的一个内部类
     * 通过RequestConfig的custom方法来获取到一个Builder对象
     * 设置builder的连接信息
     * 这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置
     * 超时时间设置
     *
     * @return
     */
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        //从连接池获取连接的超时时间
        //与服务器连接的超时时间
        //socket读取数据的超时时间
        return builder.setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout);
    }

    /**
     * 使用builder构建一个RequestConfig对象
     * @param builder
     * @return
     */
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
        return builder.build();
    }
}

/**
 * @author 董感恩
 * @date 2020-03-05 14:37:21
 * @desc 自定义重试处理器
 **/
class MyHttpRequestRetryHandler implements HttpRequestRetryHandler {

    @Override
    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        //设置重试次数
        if (executionCount > 5) {
            return false;
        }
        //设置指定异常进行重试
        if (exception instanceof UnknownHostException || exception instanceof ConnectTimeoutException
                || !(exception instanceof SSLException) || exception instanceof NoHttpResponseException) {
            return true;
        }
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        HttpRequest request = clientContext.getRequest();
        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
        if (idempotent) {
            //如果请求被认为是幂等的，那么就重试。即重复执行不影响程序其他效果的
            return true;
        }
        return false;
    }
}
