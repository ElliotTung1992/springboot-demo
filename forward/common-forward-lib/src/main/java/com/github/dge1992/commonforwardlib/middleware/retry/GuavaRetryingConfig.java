package com.github.dge1992.commonforwardlib.middleware.retry;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author 董感恩
 * @date 2020-03-05 13:49
 * GuavaRetry配置类
 */
@Configuration
public class GuavaRetryingConfig {

    @Value("${guava.retry.sleepTime}")
    private Integer sleepTime;

    @Value("${guava.retry.attemptNumber}")
    private Integer attemptNumber;

    @Bean
    public Retryer<Object> retryer() {
        Retryer<Object> retryer = RetryerBuilder.newBuilder()
                //抛出RuntimeException会进行重试
                .retryIfException()
                //抛出Error进行重试
                .retryIfExceptionOfType(Error.class)
                //对指定异常进行重试
                //.retryIfExceptionOfType(NullPointerException.class)
                //断言
                .retryIfResult(Predicates.equalTo(false))
                //重试策略
                .withWaitStrategy(WaitStrategies.fixedWait(sleepTime, TimeUnit.SECONDS))
                //尝试次数
                .withStopStrategy(StopStrategies.stopAfterAttempt(attemptNumber))
                //注册监听
                .withRetryListener(new LogRetryListener())
                .build();
        return retryer;
    }
}
