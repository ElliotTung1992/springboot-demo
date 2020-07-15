package com.github.dge1992.restfuldemo.service;

import com.github.rholder.retry.*;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.concurrent.Immutable;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 董感恩
 * @date 2020-07-14 14:51
 * @desc GuavaRetry测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuavaRetryServiceTest {

    /**
     * @author 董感恩
     * @date 2020-07-14 15:49:46
     * @desc 简单案例
     **/
    @Test
    public void simpleTest() throws ExecutionException, RetryException {
        //自己的业务逻辑
        Callable<Boolean> callable = () -> {
            System.out.println("哈哈");
            throw new IOException();
        };
        //重试器
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                //如果结果返回null重试
                .retryIfResult(Predicates.isNull())
                //如果抛IOException异常重试
                .retryIfExceptionOfType(IOException.class)
                //如果抛RuntimeException异常重试
                .retryIfRuntimeException()
                //重试3次后停止
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();
        //重试器执行业务逻辑
        retryer.call(callable);
    }

    /**
     * @author 董感恩
     * @date 2020-07-14 15:50:37
     * @desc 完整案例
     **/
    @Test
    public void completeTest() throws ExecutionException, RetryException {

        //创建等待策略
        WaitStrategy waitStrategy = WaitStrategies.incrementingWait(5, TimeUnit.SECONDS, 5, TimeUnit.SECONDS);
        /*waitStrategy = WaitStrategies.fibonacciWait();
        waitStrategy = WaitStrategies.fibonacciWait(10, TimeUnit.SECONDS);
        waitStrategy = WaitStrategies.fibonacciWait(4, 10, TimeUnit.SECONDS);
        waitStrategy = WaitStrategies.exponentialWait();
        waitStrategy = WaitStrategies.exponentialWait(10, TimeUnit.SECONDS);*/
        //创建自定义等待策略
        waitStrategy = new CustomWaitStrategy(TimeUnit.SECONDS, 1,3,5,7,9);

        //创建阻塞策略
        //默认阻塞策略，休眠线程
        BlockStrategy blockStrategy = BlockStrategies.threadSleepStrategy();
        blockStrategy = new SpinLockStrategy();

        //创建重试器
        Retryer<Object> retryer = RetryerBuilder.newBuilder()
                //重试条件
                .retryIfException()
                .retryIfRuntimeException()
                .retryIfExceptionOfType(Exception.class)
                .retryIfException(Predicates.equalTo(new Exception()))
                .retryIfResult(Predicates.equalTo(false))
                //等待策略
                .withWaitStrategy(waitStrategy)
                //停止策略
                .withStopStrategy(StopStrategies.stopAfterAttempt(10))
                //时间限制
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))
                //阻塞策略 默认是线程休眠 BlockStrategies.threadSleepStrategy()
                .withBlockStrategy(blockStrategy)
                .withRetryListener(new LogRetryListener())
                .build();



        AtomicInteger time = new AtomicInteger(0);
        //创建自定义逻辑
        Callable callable = () -> {
            time.getAndIncrement();
            System.out.println("呵呵" + time.get());
            if(time.get() == 1){
                throw new NullPointerException();
            }else if(time.get() == 2){
                throw new RuntimeException();
            }else if(time.get() == 3){
                Thread.sleep(10000l);
                throw new Exception();
            }else if(time.get() == 4){
                return false;
            }
            return true;
        };
        //执行
        retryer.call(callable);
    }

    /**
     * @author 董感恩
     * @date 2020-07-14 17:31:10
     * @desc 自定义阻塞策略,自旋锁实现
     **/
    @Immutable
    @Log4j2
    private static class SpinLockStrategy implements BlockStrategy {

        private SpinLockStrategy() {

        }

        public void block(long sleepTime) throws InterruptedException {
            LocalDateTime startTime = LocalDateTime.now();

            long start = System.currentTimeMillis();
            long end = start;

            log.info("[SpinBlockStrategy]...begin wait.");

            while (end - start <= sleepTime) {
                end = System.currentTimeMillis();
            }
            //Thread.sleep(sleepTime);

            //使用Java8新增的Duration计算时间间隔
            Duration duration = Duration.between(startTime, LocalDateTime.now());

            log.info("[SpinBlockStrategy]...end wait.duration={}", duration.toMillis());
        }
    }

    /**
     * @author 董感恩
     * @date 2020-07-14 19:13:28
     * @desc 重试日志监听
     **/
    @Log4j2
    private static class LogRetryListener implements RetryListener{

        @Override
        public <V> void onRetry(Attempt<V> attempt) {
            /*log.info("这是第{}次访问", attempt.getAttemptNumber());

            log.info("距离第一次的重试延迟{}", attempt.getDelaySinceFirstAttempt());

            if(attempt.hasException()){
                log.info("是否有异常{}：", attempt.hasException());
                log.info("错误原因是{}：", attempt.getExceptionCause());
            }else{
                log.info("是否正确返回结果：{}", attempt.hasResult());
                log.info("正确结果是：{}", attempt.getResult());
            }*/
        }
    }

    /**
     * @author 董感恩
     * @date 2020-07-14 20:12:54
     * @desc 自定义重试策略
     **/
    private static class CustomWaitStrategy implements WaitStrategy{

        private TimeUnit timeUnit;

        private long[] intervals;

        public CustomWaitStrategy(TimeUnit timeUnit, long... interval){
            Preconditions.checkNotNull(timeUnit, "timeUnit不能为空");
            Preconditions.checkNotNull(interval, "interval不能为空");
            this.timeUnit = timeUnit;
            this.intervals = interval;
        }

        @Override
        public long computeSleepTime(Attempt attempt) {
            Integer attemptNumber = Math.toIntExact(attempt.getAttemptNumber());
            Integer index = intervals.length >= attemptNumber ? attemptNumber : intervals.length;
            long interval = intervals[index-1];
            return timeUnit.toMillis(interval);
        }
    }

}