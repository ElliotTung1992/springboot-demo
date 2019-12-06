/*
 * @(#)DistributedLockAspect.java 2018年3月15日 下午7:18:07
 * Copyright 2018 施建波, Inc. All rights reserved. cargogm.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.dge1992.redisson.aop.distributedLock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class DistributedLockAspect extends AbstractDistributedLockAspect {

	@Pointcut("@annotation(com.github.dge1992.redisson.aop.distributedLock.DistributedLock)")
    public void DistributedLockAspect() {}

    @Around(value = "DistributedLockAspect()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        return super.doAround(pjp);
    }

    @AfterThrowing(value = "DistributedLockAspect()", throwing="ex")
    public void afterThrowing(Throwable ex) {
        super.afterThrowing(ex);
    }

    @SuppressWarnings("rawtypes")
    public String getLockName(Class targetClass, Method method, Object[] args) {
    	if(null == method){
    		 throw new NullPointerException();
    	}
        DistributedLock annotation = method.getAnnotation(DistributedLock.class);

        String lockName = annotation.lockName(),
                param = annotation.param();

        if (isEmpty(lockName)) {
            if (args.length > 0) {
                if (isNotEmpty(param)) {
                    Object arg;
                    if (annotation.argNum() > 0) {
                        arg = args[annotation.argNum() - 1];
                    } else {
                        arg = args[0];
                    }
                    lockName = String.valueOf(getParam(arg, param));
                } else if (annotation.argNum() > 0) {
                    lockName = args[annotation.argNum() - 1].toString();
                }
            }
        }

        if (isNotEmpty(lockName)) {
            String preLockName = annotation.lockNamePre(),
                    postLockName = annotation.lockNamePost(),
                    separator = annotation.separator();

            StringBuilder lName = new StringBuilder();
            if (isNotEmpty(preLockName)) {
                lName.append(preLockName).append(separator);
            }
            lName.append(lockName);
            if (isNotEmpty(postLockName)) {
                lName.append(separator).append(postLockName);
            }

            lockName = lName.toString();

            return lockName;
        }

        throw new IllegalArgumentException("Can't get or generate lockName accurately!");
    }

    private boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    private boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }
}
