package com.github.dge1992.commonforward.lib.middleware.retry;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 董感恩
 * @date 2020-03-04 20:47:30
 * 自定义重试监听 - 日志记录
 **/
public class LogRetryListener implements RetryListener {

    private Logger logger = LoggerFactory.getLogger(LogRetryListener.class);

    @Override
    public <V> void onRetry(Attempt<V> attempt) {
        logger.info("第{}次调用", attempt.getAttemptNumber());
        logger.info("距离上一次的延迟{}", attempt.getDelaySinceFirstAttempt());
        if(attempt.hasException()){
            logger.info("错误信息: {}", attempt.getExceptionCause());
        }else{
            logger.info("返回结果是: {}", attempt.getResult());
        }
    }
}
