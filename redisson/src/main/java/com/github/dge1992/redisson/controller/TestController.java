package com.github.dge1992.redisson.controller;

import com.github.dge1992.redisson.service.RedissonService;
import lombok.extern.log4j.Log4j2;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author 小眼睛带鱼
 * @date 2019-12-05 09:53
 * @desc
 */
@Log4j2
@RestController
public class TestController {

    @Autowired
    private RedissonService redissonService;

    @RequestMapping("/test")
    public Object test(){
        RLock lock = redissonService.getRLock("test");
        try {
            boolean b = false;
            while (!b){
                b = lock.tryLock(1, 5, TimeUnit.SECONDS);
                if (b){
                    log.info("获得锁");
                }else{
                    log.info("获得锁失败");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "获得可重入锁失败";
        }finally {
            lock.unlock();
        }
        return "获得可重入锁成功";
    }

    @RequestMapping("/test2/{lockName}")
    public Object test2(@PathVariable String lockName){
        RLock lock = redissonService.getRLock(lockName);
        lock.lock();
        return "获得可重入锁成功";
    }

    @RequestMapping("/test3")
    public Object test3(){
        RLock lock = redissonService.getRLock("test3");
        lock.lock(10, TimeUnit.SECONDS);
        return "获取锁成功";
    }

    @RequestMapping("/test4")
    public Object test4(){
        RLock lock = redissonService.getRLock("test3");
        Future<Boolean> res = lock.tryLockAsync(100, 10, TimeUnit.SECONDS);
        log.info(res.isDone());
        return "获取锁成功";
    }

    @RequestMapping("/test5")
    public Object test5(){
        RLock lock = redissonService.getFairLock("test5");
        try {
            boolean b = false;
            while (!b){
                b = lock.tryLock(1, 5, TimeUnit.SECONDS);
                if (b){
                    log.info("获得锁");
                }else{
                    log.info("获得锁失败");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "获得公平锁失败";
        }finally {
            lock.unlock();
        }
        return "获得可公平锁成功";
    }

    @RequestMapping("test6")
    public Object test6(){
        RLock lock1 = redissonService.getRLock("lock1");
        RLock lock2 = redissonService.getRLock("lock2");
        RLock lock3 = redissonService.getRLock("lock3");

        RedissonMultiLock multiLock = redissonService.getMultiLock(lock1, lock2, lock3);
        multiLock.lock();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        multiLock.unlock();

        return "获取联锁成功";
    }

    @RequestMapping("test7")
    public Object test7(){
        RLock lock1 = redissonService.getRLock("lock1");
        RLock lock2 = redissonService.getRLock("lock2");
        RLock lock3 = redissonService.getRLock("lock3");

        RedissonRedLock redLock = redissonService.getRedLock(lock1, lock2, lock3);
        redLock.lock();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        redLock.unlock();

        return "获取红锁成功";
    }

    @RequestMapping("test8")
    public Object test8(){
        RSemaphore semaphore = redissonService.getSemaphore("test9");
        try {
            semaphore.acquire();
//            Thread.sleep(20000);
//            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "获取信号量成功";
    }
}
