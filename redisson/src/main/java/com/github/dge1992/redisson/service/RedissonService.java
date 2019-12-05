package com.github.dge1992.redisson.service;

import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 小眼睛带鱼
 * @date 2019-12-05 09:50
 * @desc
 */
@Service
public class RedissonService {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * @author 小眼睛带鱼
     * @date 2019-12-05 11:04:17
     * @desc 获取可重入锁
     **/
    public RLock getRLock(String lockName) {
        RLock rLock = redissonClient.getLock(lockName);
        return rLock;
    }

    /**
     * @author 小眼睛带鱼
     * @date 2019-12-05 11:36:46
     * @desc 获取公平锁 优先分配给先发出请求的线程
     **/
    public RLock getFairLock(String lockName){
        RLock fairLock = redissonClient.getFairLock(lockName);
        return fairLock;
    }

    /**
     * 
     * @author 小眼睛带鱼
     * @date 2019-12-05 13:18:18
     * @desc 获取联锁
     *  可以将多个RLock对象关联为一个联锁
     *  所有的锁都上锁成功才算成功
     **/
    public RedissonMultiLock getMultiLock(RLock... locks){
        return new RedissonMultiLock(locks);
    }

    /**
     *
     * @author 小眼睛带鱼
     * @date 2019-12-05 13:39:34
     * @desc 获取红锁
     *   红锁在大部分节点上加锁成功就算成功
     **/
    public RedissonRedLock getRedLock(RLock... locks){
        return new RedissonRedLock(locks);
    }

    /**
     * 
     * @author 小眼睛带鱼
     * @date 2019-12-05 14:05:50
     * @desc 获取读写锁
     *   读锁不互斥 写锁互斥
     **/
    public RReadWriteLock getReadWriteLock(String lockName){
        return redissonClient.getReadWriteLock(lockName); 
    }
    
    /**
     * 
     * @author 小眼睛带鱼
     * @date 2019-12-05 14:09:07
     * @desc 获取信号量
     **/
    public RSemaphore getSemaphore(String name){
        return redissonClient.getSemaphore(name);
    }

    /**
     * 
     * @author 小眼睛带鱼
     * @date 2019-12-05 14:19:05
     * @desc 获得可过期性信号量
     **/
    public RPermitExpirableSemaphore getPermitExpirableSemaphore(String name){
        return redissonClient.getPermitExpirableSemaphore(name);
    }

    /**
     * 
     * @author 小眼睛带鱼
     * @date 2019-12-05 14:20:51
     * @desc 获取闭锁
     **/
    public RCountDownLatch getCountDownLatch(String name){
        return redissonClient.getCountDownLatch(name);
    }
}
