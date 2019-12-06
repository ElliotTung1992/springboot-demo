/*
 * @(#)SingleDistributedLockTemplate.java 2018年3月15日 下午7:01:29
 * Copyright 2018 施建波, Inc. All rights reserved. cargogm.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.dge1992.redisson.aop.distributedLock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>File：SingleDistributedLockTemplate.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2018年3月15日 下午7:01:29</p>
 * <p>Company: cargogm.com</p>
 * @author 施建波
 * @version 1.0
 */
@Component
public class SingleDistributedLockTemplate implements DistributedLockTemplate{
	
	private static final Logger logger = LoggerFactory.getLogger(SingleDistributedLockTemplate.class);

	@Autowired
	private RedissonClient redisson;
    
	@Override
	public <T> T lock(DistributedLockCallback<T> callback, boolean fairLock) {
		return lock(callback, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT, fairLock);
	}

	@Override
	public <T> T lock(DistributedLockCallback<T> callback, long leaseTime,
			TimeUnit timeUnit, boolean fairLock) {
		RLock lock = getLock(callback.getLockName(), fairLock);
        try {
            lock.lock(leaseTime, timeUnit);
            return callback.process();
        } finally {
        	try{
	            if (lock != null && lock.isLocked()) {
	                lock.unlock();
	            }
        	}catch(Exception e){
        		logger.error(e.getMessage(), e);
        	}
        }
	}

	@Override
	public <T> T tryLock(DistributedLockCallback<T> callback, boolean fairLock) {
		return tryLock(callback, DEFAULT_WAIT_TIME, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT, fairLock);
	}

	@Override
	public <T> T tryLock(DistributedLockCallback<T> callback, long waitTime,
			long leaseTime, TimeUnit timeUnit, boolean fairLock) {
		RLock lock = getLock(callback.getLockName(), fairLock);
        try {
            if (lock.tryLock(waitTime, leaseTime, timeUnit)) {
                return callback.process();
            }
        } catch (InterruptedException e) {

        } finally {
        	try{
	            if (lock != null && lock.isLocked()) {
	                lock.unlock();
	            }
        	}catch(Exception e){
        		logger.error(e.getMessage(), e);
        	}
        }
        return null;
	}
	
	private RLock getLock(String lockName, boolean fairLock) {
        RLock lock;
        if (fairLock) {
            lock = redisson.getFairLock(lockName);
        } else {
            lock = redisson.getLock(lockName);
        }
        return lock;
    }
}
