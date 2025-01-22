package com.github.dge1992.fish.spring.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 对多个接口创建代理对象
 */
public class CglibTestTwo {



    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 设置代理类的父类
        enhancer.setSuperclass(Poultry.class);
        // 设置代理类需要实现的接口
        enhancer.setInterfaces(new Class[]{Fly.class, Run.class});
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                long start = System.currentTimeMillis();
                Object result = null;
                if(method.getName().equals("layAnEgg")){
                    result = methodProxy.invokeSuper(o, objects);
                }
                System.out.println(method + ",耗时:" + (System.currentTimeMillis() - start));
                return result;
            }
        });
        // 创建代理对象
        Object proxy = enhancer.create();
        if(proxy instanceof Poultry){
            ((Poultry)proxy).layAnEgg();
        }
        if(proxy instanceof Fly){
            ((Fly)proxy).fly();
        }
        if(proxy instanceof Run){
            ((Run)proxy).run();
        }
        // 查看代理对象的类型
        System.out.println("代理对象的类型是:" + proxy.getClass());
        // 代理对象的父类
        System.out.println("代理对象父类的类型是:" + proxy.getClass().getSuperclass());
        // 代理对象实现的接口
        for (Class<?> anInterface : proxy.getClass().getInterfaces()) {
            System.out.println("代理对象实现的接口:" + anInterface);
        }
    }
}
