package com.github.dge1992.redisson.controller;

import com.github.dge1992.redisson.aop.distributedLock.DistributedLock;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

/**
 * @author 小眼睛带鱼
 * @date 2019-12-06 10:39
 * @desc 分布式事务锁测试类
 */
@Log4j2
@RestController
public class TestDistributedLockController {

    @DistributedLock(lockNamePre = "m3", lockNamePost = "first", fairLock = true, tryLock = true, argNum = 0, param = "age",
                    waitTime = 5, leaseTime = 10)
    @RequestMapping("dtest")
    public Object test(@RequestBody User user){
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("lol ");
        return "测试分布式事务锁";
    }

}
