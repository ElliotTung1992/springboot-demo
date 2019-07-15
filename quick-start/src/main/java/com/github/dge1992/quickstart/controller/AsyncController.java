package com.github.dge1992.quickstart.controller;

import com.github.dge1992.quickstart.handler.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/15
 **/
@RequestMapping("/async")
@RestController
public class AsyncController {

    @Autowired
    AsyncTask asyncTask;

    @RequestMapping("/synchronization")
    public Object synchronization() throws Exception {
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
        return "操作成功!";
    }

    @RequestMapping("/async")
    public Object async() throws Exception {
        asyncTask.doAsyncTaskOne();
        asyncTask.doAsyncTaskTwo();
        asyncTask.doAsyncTaskThree();
        return "操作成功!";
    }

    @RequestMapping("/asyncReturn")
    public Object asyncReturn() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> future = asyncTask.doAsyncTaskOneReturn();
        Future<String> futureTwo = asyncTask.doAsyncTaskTwoReturn();
        Future<String> futureThree = asyncTask.doAsyncTaskThreeReturn();
        while (true){
            if(future.isDone() && futureTwo.isDone() && futureThree.isDone()){
                long end = System.currentTimeMillis();
                System.out.println("执行成功!");
                System.out.println("完成三个任务，耗时：" + (end - start) + "毫秒");
                break;
            }
        }
        return "操作成功!";
    }
}
