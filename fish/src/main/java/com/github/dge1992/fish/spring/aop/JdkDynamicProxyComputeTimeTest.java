package com.github.dge1992.fish.spring.aop;

import java.lang.reflect.Proxy;

public class JdkDynamicProxyComputeTimeTest {

    public static void main(String[] args) {
        Bird target = new Bird();
        JdkDynamicProxyComputeTime jdkDynamicProxyComputeTime = new JdkDynamicProxyComputeTime(target);
        // 创建代理对象
        Object proxy =
                Proxy.newProxyInstance(target.getClass().getClassLoader(),
                        new Class[]{Fly.class}, jdkDynamicProxyComputeTime);

        System.out.println(proxy instanceof Bird);
        System.out.println(proxy instanceof Fly);

        // 调用代理对象
        Fly fly = (Fly) proxy;
        fly.fly();

        System.out.println("代理对象的类型是:" + proxy.getClass());
    }
}
