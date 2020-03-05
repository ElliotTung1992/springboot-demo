package com.github.dge1992.restfuldemo.retry;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author 董感恩
 * @date 2020-03-05 13:49
 * @desc GuavaRetry配置类
 */
@Configuration
public class GuavaRetryingConfig {

    @Value("${guava.retry.sleepTime}")
    private Integer sleepTime;

    @Value("${guava.retry.attemptNumber}")
    private Integer attemptNumber;

    @Bean
    public Retryer<Object> retryer(){
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

/**
 * @author 董感恩
 * @date 2020-03-04 20:47:30
 * @desc 自定义重试监听 - 日志记录
 **/
@Log4j2
class LogRetryListener implements RetryListener {

    @Override
    public <V> void onRetry(Attempt<V> attempt) {
        log.info("第" + attempt.getAttemptNumber() + "次调用！");
        log.info("距离上一次的延迟：" + attempt.getDelaySinceFirstAttempt());
        if(attempt.hasException()){
            log.info("报错原因：" + attempt.getExceptionCause());
        }else{
            log.info("返回结果是：" + attempt.getResult());
        }
    }
}
