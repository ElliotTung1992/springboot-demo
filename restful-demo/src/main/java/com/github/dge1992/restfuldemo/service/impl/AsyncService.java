package com.github.dge1992.restfuldemo.service.impl;

import com.github.dge1992.restfuldemo.service.IAsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author 董感恩
 * @date 2020-07-23 16:58
 * @desc
 */
@Service
public class AsyncService implements IAsyncService {

    public void async(){
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("呵呵");
    }

    @Async
    public Future<String> asyncHasResult(int i){
        try {
            // 这个方法需要调用500毫秒
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 消息汇总
        return new AsyncResult<>(String.format("这个是第{%s}个异步调用的证书", i));
    }
}
