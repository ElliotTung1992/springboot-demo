/*
 * @(#)DistributedLockTemplate.java 2018年3月15日 下午6:56:27
 * Copyright 2018 施建波, Inc. All rights reserved. cargogm.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.dge1992.redisson.aop.distributedLock;

import java.util.concurrent.TimeUnit;

public interface DistributedLockTemplate {

	long DEFAULT_WAIT_TIME = 30;
    long DEFAULT_TIMEOUT   = 5;
    TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    
    /**
     * 使用分布式锁，使用锁默认超时时间。
     * @param callback
     * @param fairLock	是否使用公平锁
     * @return
     * @author 施建波  2018年3月15日 下午6:58:26
     */
    <T> T lock(DistributedLockCallback<T> callback, boolean fairLock);

    /**
     * 使用分布式锁。自定义锁的超时时间
     * @param callback
     * @param leaseTime	锁超时时间。超时后自动释放锁。
     * @param timeUnit	时间单位
     * @param fairLock	是否使用公平锁
     * @return
     * @author 施建波  2018年3月15日 下午6:59:11
     */
    <T> T lock(DistributedLockCallback<T> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock);

    /**
     * 尝试分布式锁，使用锁默认等待时间、超时时间。
     * @param callback
     * @param fairLock	是否使用公平锁
     * @return
     * @author 施建波  2018年3月15日 下午6:59:59
     */
    <T> T tryLock(DistributedLockCallback<T> callback, boolean fairLock);

    /**
     * 尝试分布式锁，自定义等待时间、超时时间。
     * @param callback
     * @param waitTime	获取锁最长等待时间
     * @param leaseTime	锁超时时间。超时后自动释放锁。
     * @param timeUnit	时间单位
     * @param fairLock	是否使用公平锁
     * @return
     * @author 施建波  2018年3月15日 下午7:00:29
     */
    <T> T tryLock(DistributedLockCallback<T> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock);
}
