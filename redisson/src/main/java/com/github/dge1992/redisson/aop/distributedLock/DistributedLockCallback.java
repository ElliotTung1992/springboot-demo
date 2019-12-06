/*
 * @(#)DistributedLockCallback.java 2018年3月15日 下午6:54:58
 * Copyright 2018 施建波, Inc. All rights reserved. cargogm.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.dge1992.redisson.aop.distributedLock;

/**
 * <p>File：DistributedLockCallback.java</p>
 * <p>Title: 分布式锁回调接口</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2018年3月15日 下午6:54:58</p>
 * <p>Company: cargogm.com</p>
 * @author 施建波
 * @version 1.0
 */
public interface DistributedLockCallback<T> {

	/**
	 * 此处实现分布式锁的业务逻辑
	 * @return
	 * @author 施建波  2018年3月15日 下午6:55:24
	 */
    T process();

    /**
     * 获取分布式锁名称
     * @return
     * @author 施建波  2018年3月15日 下午6:55:56
     */
    String getLockName();
}
