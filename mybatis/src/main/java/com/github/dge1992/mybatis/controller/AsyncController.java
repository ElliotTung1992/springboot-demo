package com.github.dge1992.mybatis.controller;

import com.github.dge1992.mybatis.service.IAsyncService;
import com.github.dge1992.mybatis.utils.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author 董感恩
 * @date 2020-07-23 16:46
 * @desc 异步注解测试
 *
 *
 *  1. 添加注解@EnableAsync开启异步
 *  2. 实现原理是aop,同一个类中的一个方法调用另一个方法会失效
 *  3. 当异步有返回值时，一定要批量读取结果, 否则不能达到异步的效果!!
 *
 */
@RequestMapping("/async")
@RestController
public class AsyncController {

    @Autowired
    private IAsyncService asyncService;

    /**
     * @author 董感恩
     * @date 2020-07-23 16:48:00
     * @desc 测试异步
     **/
    @RequestMapping("/testAsync")
    public void testAsync() {
        asyncService.async();

        //第一种解决方案:
        AsyncController asyncController = SpringContextHolder.getBean("asyncController");
        asyncController.async();

        System.out.println("哈哈");
    }

    @Async
    public void async(){
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("嘻嘻");
    }

    @GetMapping("/testAsync2")
    public Map<String, Object> testAsyncReturn() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        Map<String, Object> map = new HashMap<>();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> future = asyncService.asyncHasResult(i);
            futures.add(future);
        }
        List<String> response = new ArrayList<>();
        for (Future future : futures) {
            String string = (String) future.get();
            response.add(string);
        }
        map.put("data", response);
        map.put("消耗时间", String.format("任务执行成功,耗时{%s}毫秒", System.currentTimeMillis() - start));
        return map;
    }
}
