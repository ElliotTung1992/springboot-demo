package com.github.dge1992.fish.spring.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 对多个接口创建代理对象
 */
public class CglibTestOne {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{Fly.class, Run.class});
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("方法名称:" + method.getName());
                return null;
            }
        });
        // 创建代理对象
        Object proxy = enhancer.create();
        if(proxy instanceof Fly){
            ((Fly)proxy).fly();
        }
        if(proxy instanceof Run){
            ((Run)proxy).run();
        }
        System.out.println("代理对象的类型:" + proxy.getClass());
        System.out.println("代理对象的父类型:" + proxy.getClass().getSuperclass());
        for (Class<?> anInterface : proxy.getClass().getInterfaces()) {
            System.out.println("接口的类型是:" + anInterface);
        }
    }
}
