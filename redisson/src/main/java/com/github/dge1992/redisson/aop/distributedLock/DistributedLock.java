/*
 * @(#)DistributedLock.java 2018年3月15日 下午7:12:18
 * Copyright 2018 施建波, Inc. All rights reserved. cargogm.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.dge1992.redisson.aop.distributedLock;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {

	/**
	 * 锁的名称
	 * 如果lockName可以确定，直接设置该属性。
	 * @return
	 * @author 施建波  2018年3月15日 下午7:14:26
	 */
    String lockName() default "";

    /**
     * lockName前缀
     * @return
     * @author 施建波  2018年3月15日 下午7:14:42
     */
    String lockNamePre() default "";

    /**
     * lockName后缀
     * @return
     * @author 施建波  2018年3月15日 下午7:14:51
     */
    String lockNamePost() default "lock";

    /**
     * 获得锁名时拼接前后缀用到的分隔符
     * @return
     * @author 施建波  2018年3月15日 下午7:15:59
     */
    String separator() default ".";

    /**
     * 获取注解的方法参数列表的某个参数对象的某个属性值来作为lockName。因为有时候lockName是不固定的。
     * 当param不为空时，可以通过argNum参数来设置具体是参数列表的第几个参数，不设置则默认取第一个。
     * @return
     * @author 施建波  2018年3月15日 下午7:16:09
     */
    String param() default "";
    
    /**
     * 将方法第argNum个参数作为锁
     * @return
     * @author 施建波  2018年3月15日 下午7:16:23
     */
    int argNum() default 1;
    
    /**
     * 是否使用公平锁。
     * 公平锁即先来先得。
     * @return
     * @author 施建波  2018年3月15日 下午7:16:36
     */
    boolean fairLock() default false;
    
    /**
     * 是否使用尝试锁。
     * @return
     * @author 施建波  2018年3月15日 下午7:16:53
     */
    boolean tryLock() default false;
    
    /**
     * 最长等待时间。
     * 该字段只有当tryLock()返回true才有效。
     * @return
     * @author 施建波  2018年3月15日 下午7:17:02
     */
    long waitTime() default 30L;
    
    /**
     * 锁超时时间。
     * 超时时间过后，锁自动释放。
     * @return
     * @author 施建波  2018年3月15日 下午7:17:20
     */
    long leaseTime() default 20L;
    
    /**
     * 时间单位。默认为秒
     * @return
     * @author 施建波  2018年3月15日 下午7:17:40
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
