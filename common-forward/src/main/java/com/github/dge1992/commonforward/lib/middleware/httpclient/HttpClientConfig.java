package com.github.dge1992.commonforward.lib.middleware.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 董感恩
 * @date 2020-02-24 17:00
 * @desc HttpClient配置类
 */
@Configuration
public class HttpClientConfig {

    //@Value("${http.maxTotal}")
    private Integer maxTotal = 100;

    //@Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute = 20;

    //@Value("${http.connectTimeout}")
    private Integer connectTimeout = 2000;

    //@Value("${http.connectionRequestTimeout}")
    private Integer connectionRequestTimeout = 1000;

    //@Value("${http.socketTimeout}")
    private Integer socketTimeout = 20000;

    //@Value("${http.staleConnectionCheckEnabled}")
    private boolean staleConnectionCheckEnabled = true;

    /**
     * 连接池管理器，设置最大连接数、并发连接数
     *
     * @return org.apache.http.impl.conn.PoolingHttpClientConnectionManager
     * @author dge
     * @date 2021-01-20 13:57
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        //最大连接数
        httpClientConnectionManager.setMaxTotal(maxTotal);
        //路由并发数
        httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return httpClientConnectionManager;
    }

    /**
     * 实例化连接池，设置连接池管理器。
     * 这里需要以参数形式注入上面实例化的连接池管理器
     *
     * @param httpClientConnectionManager 连接管理器
     * @param requestConfig               请求配置
     * @return org.apache.http.impl.client.HttpClientBuilder
     * @author dge
     * @date 2021-01-20 13:58
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
        httpClientBuilder.disableAutomaticRetries();
        return httpClientBuilder;
    }

    /**
     * 注入连接池，用于获取httpClient
     *
     * @param httpClientBuilder 构建对象
     * @return org.apache.http.impl.client.CloseableHttpClient
     * @author dge
     * @date 2021-01-20 13:58
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
     *
     * @return org.apache.http.client.config.RequestConfig.Builder
     * @author dge
     * @date 2021-01-20 13:59
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
     *
     * @param builder 构建对象
     * @return org.apache.http.client.config.RequestConfig
     * @author dge
     * @date 2021-01-20 13:59
     */
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
        return builder.build();
    }
}
