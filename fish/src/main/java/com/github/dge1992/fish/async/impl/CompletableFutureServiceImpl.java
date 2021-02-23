package com.github.dge1992.fish.async.impl;

import com.github.dge1992.fish.async.CompletableFutureService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-25 14:14
 */
@Service
public class CompletableFutureServiceImpl implements CompletableFutureService {

    private static final Double D = 0.3;

    @Override
    public void testCompletableFuture() {


    }

    /**
     * 案例1
     * @author dge
     * @date 2021-02-23 15:10
     */
    private void oneCase(){
        //简单执行completableFuture小案例
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        })
                .thenApply(i -> i + 1)
                .thenApply(i -> i * i)
                .whenComplete((r, e) -> System.out.println(r));

        System.out.println("哈哈");

        //简单执行CompletableFuture小案例
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> s + " world")
                .thenApply(String::toUpperCase)
                .whenComplete((r, e) -> System.out.println(r));

        //小案例
        CompletableFuture.supplyAsync(() -> 5)
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 5)
                .thenRun(() -> System.out.println("haha"));


        //合并
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> s + " world")
                .thenApply(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture(" java"),(s1, s2) -> s1 + s2)
                .thenAccept(System.out::println);

        //whenComplete
        String[] strList = {"a", "b", "c"};
        List<CompletableFuture> list = new ArrayList<>();
        for (String str : strList) {
            list.add(CompletableFuture.supplyAsync(() -> str).thenApply(String::toUpperCase));
        }
        CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]))
                .whenComplete((r, e) -> {
                    if(Objects.nonNull(e)){
                        throw new RuntimeException(e);
                    }
                });
    }

    /**
     * 案例2
     * 异步任务正常结束时会自动执行正确的回调方法
     * 异步任务异常结束时会自动执行异常的回调方法
     * @author dge
     * @date 2021-02-23 16:00
     */
    private void caseTwo(){
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(CompletableFutureServiceImpl::fetchPrice);

        //正确执行
        future.thenAccept(result -> System.out.println("result is :" + result));

        //执行异常
        future.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
    }

    /**
     * 案例3
     * 串行执行
     * @author dge
     * @date 2021-02-23 16:19
     */
    private void caseThree(){
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> CompletableFutureServiceImpl.queryCodeByName("旺仔牛奶"));

        CompletableFuture<Double> future = completableFuture
                .thenApplyAsync(code -> CompletableFutureServiceImpl.fetchPrice(code));

        future.thenAccept(e -> System.out.println("price is:" + e));
    }

    /**
     * 案例4
     * 并行处理
     * @author dge
     * @date 2021-02-23 17:58
     */
    private void caseFour(){
        CompletableFuture<String> completableFutureOne = CompletableFuture
                .supplyAsync(() -> CompletableFutureServiceImpl.queryCodeByName("旺仔牛奶"));
        CompletableFuture<String> completableFutureTwo = CompletableFuture
                .supplyAsync(() -> CompletableFutureServiceImpl.queryCodeByName("辣条"));

        CompletableFuture<Object> completableFutureCode = CompletableFuture
                .anyOf(completableFutureOne, completableFutureTwo);

        CompletableFuture<Double> completableFutureThree = completableFutureCode
                .thenApplyAsync(code -> CompletableFutureServiceImpl.fetchPrice((String) code, "a"));
        CompletableFuture<Double> completableFutureFour = completableFutureCode
                .thenApplyAsync(code -> CompletableFutureServiceImpl.fetchPrice((String) code, "b"));

        CompletableFuture<Object> completableFuture = CompletableFuture.anyOf(completableFutureThree, completableFutureFour);

        completableFuture.thenAccept(System.out::println);
    }

    /**
     * 根据name获取code
     * @param name name
     * @return java.lang.String
     * @author dge
     * @date 2021-02-23 16:13
     */
    private static String queryCodeByName(String name){
        String code = null;
        if(StringUtils.isNotBlank(name) && "旺仔牛奶".equals(name)){
            code = "wznn";
        }
        if(StringUtils.isNotBlank(name) && "辣条".equals(name)){
            code = "lt";
        }
        return code;
    }

    /**
     * 根据code获取price
     * @param code code
     * @return java.lang.Double
     * @author dge
     * @date 2021-02-23 16:13
     */
    private static Double fetchPrice(String code){
        Double d = null;
        if (StringUtils.isNotBlank(code) && "wznn".equals(code)){
            return 0.5;
        }
        if (StringUtils.isNotBlank(code) && "lt".equals(code)){
            return 0.3;
        }
        return d;
    }

    /**
     * 根据code获取price
     * @param code code
     * @return java.lang.Double
     * @author dge
     * @date 2021-02-23 16:13
     */
    private static Double fetchPrice(String code, String mark){
        Double d = null;
        if (StringUtils.isNotBlank(code) && "wznn".equals(code) && "a".equals(mark)){
            return 0.5;
        }
        if (StringUtils.isNotBlank(code) && "lt".equals(code) && "b".equals(mark)){
            return 0.3;
        }
        throw new RuntimeException();
    }

    /**
     * 获取单价
     * @return java.lang.Double
     * @author dge
     * @date 2021-02-23 15:51
     */
    private static Double fetchPrice(){
        double random = Math.random();
        if(random < D){
            throw new RuntimeException();
        }
        return random;
    }
}