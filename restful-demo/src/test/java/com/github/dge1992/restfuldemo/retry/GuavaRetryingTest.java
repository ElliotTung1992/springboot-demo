package com.github.dge1992.restfuldemo.retry;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

/**
 * @author 董感恩
 * @date 2020-03-05 13:59
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuavaRetryingTest {

    @Autowired
    private Retryer retryer;

    /**
     * @author 董感恩
     * @date 2020-03-05 14:05:12
     * @desc 断言重试
     **/
    @Test
    public void testRetryer(){
        try {
            retryer.call(() -> goRetry());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author 董感恩
     * @date 2020-03-05 14:05:46
     * @desc 异常重试
     **/
    @Test
    public void testRetryerException(){
        try {
            retryer.call(() -> goRetryException());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author 董感恩
     * @date 2020-03-05 14:05:46
     * @desc Error重试
     **/
    @Test
    public void testRetryerError(){
        try {
            retryer.call(() -> goRetryError());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }
    }

    private Object goRetryError() {
        throw  new OutOfMemoryError();
    }

    private Object goRetryException() {
        int i = 10 / 0;
        return "测试异常重试！";
    }

    private Boolean goRetry() {
        return false;
    }
}
