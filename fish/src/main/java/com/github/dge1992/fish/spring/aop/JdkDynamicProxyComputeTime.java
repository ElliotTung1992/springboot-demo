package com.github.dge1992.fish.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理计算时间
 */
public class JdkDynamicProxyComputeTime implements InvocationHandler {

    private Object target;

    public JdkDynamicProxyComputeTime(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        System.out.println(method + ",耗时:" + (System.currentTimeMillis() - start));
        return result;
    }
}
