package com.github.dge1992.redisson.aop.distributedLock;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.client.RedisException;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public abstract class AbstractDistributedLockAspect {

    @Autowired
    private DistributedLockTemplate lockTemplate;

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //切点所在的类
        Class targetClass = pjp.getTarget().getClass();
        //使用了注解的方法
        String methodName = pjp.getSignature().getName();

        Class[] parameterTypes = ((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes();

        Method method = targetClass.getMethod(methodName, parameterTypes);

        Object[] arguments = pjp.getArgs();

        final String lockName = getLockName(targetClass, method, arguments);

        return lock(pjp, method, lockName);
    }

    public void afterThrowing(Throwable ex) {
        if(ex instanceof RuntimeException) {
            throw (RuntimeException) ex;
        } else {
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("rawtypes")
    public abstract String getLockName(Class targetClass, Method method, Object[] args);

    public Object getParam(Object arg, String param) {
        if (isNotEmpty(param) && arg != null) {
            try {
                Object result = PropertyUtils.getProperty(arg, param);
                return result;
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException(arg + "没有属性" + param + "或未实现get方法。", e);
            } catch (Exception e) {
                throw new RuntimeException("", e);
            }
        }
        return null;
    }

    public Object lock(ProceedingJoinPoint pjp, Method method, final String lockName) {

    	try{
	        DistributedLock annotation = method.getAnnotation(DistributedLock.class);
	
	        boolean fairLock = annotation.fairLock();
	
	        boolean tryLock = annotation.tryLock();
	
	        if (tryLock) {
	            return tryLock(pjp, annotation, lockName, fairLock);
	        } else {
	            return lock(pjp, annotation, lockName, fairLock);
	        }
    	}catch(RedisException e){
    		
    	}
    	return proceed(pjp);
    }

    public Object lock(final ProceedingJoinPoint pjp, DistributedLock annotation, final String lockName, boolean fairLock) {
        long leaseTime = annotation.leaseTime();
        TimeUnit timeUnit = annotation.timeUnit();

        return lockTemplate.lock(new DistributedLockCallback<Object>() {
            @Override
            public Object process() {
                return proceed(pjp);
            }

            @Override
            public String getLockName() {
                return lockName;
            }
        }, leaseTime, timeUnit, fairLock);
    }

    public Object tryLock(final ProceedingJoinPoint pjp, DistributedLock annotation, final String lockName, boolean fairLock) {

        long waitTime = annotation.waitTime(),
                leaseTime = annotation.leaseTime();
        TimeUnit timeUnit = annotation.timeUnit();

        return lockTemplate.tryLock(new DistributedLockCallback<Object>() {
            @Override
            public Object process() {
                return proceed(pjp);
            }

            @Override
            public String getLockName() {
                return lockName;
            }
        }, waitTime, leaseTime, timeUnit, fairLock);
    }

    public Object proceed(ProceedingJoinPoint pjp) {
        try {
            return pjp.proceed();
        } catch (RuntimeException throwable) {
            throw throwable;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    private boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    private boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }
}
