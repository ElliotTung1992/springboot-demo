package com.github.dge1992.fish.pattern.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-12 21:27
 */
public class Test {

    public static void main(String[] args) throws Exception {
        //testOne();
        //testTwo();
        //testThree();
        //testFour();
        testFive();
    }

    /**
     * 测试枚举单例模式
     * @author dge
     * @date 2021-04-12 22:34
     */
    private static void testFive() throws Exception {
        SingleEnum instance1 = SingleEnum.getInstance();
        SingleEnum instance2 = SingleEnum.getInstance();
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());

        Constructor<SingleEnum> declaredConstructor = SingleEnum.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        SingleEnum singleEnum = declaredConstructor.newInstance(null);
        System.out.println(singleEnum);
    }

    /**
     * 通过反射破坏单例模式
     * @author dge
     * @date 2021-04-12 22:07
     */
    private static void testFour() throws Exception {
        //DemoTwo instance = DemoTwo.getInstance();
        Constructor<DemoTwo> declaredConstructor = DemoTwo.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        DemoTwo instance = declaredConstructor.newInstance();
        Field dge = DemoTwo.class.getDeclaredField("dge");
        dge.setAccessible(true);
        dge.set(instance, false);
        DemoTwo instance1 = declaredConstructor.newInstance();
        System.out.println(instance);
        System.out.println(instance1);
    }

    /**
     * 测试静态内部类
     * @author dge
     * @date 2021-04-12 22:00
     */
    private static void testThree() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Holder instance = Holder.getInstance();
                System.out.println(instance);
            }).start();
        }
    }

    /**
     * 测试懒汉式
     * @author dge
     * @date 2021-04-12 21:34
     */
    private static void testTwo() {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                DemoTwo demoTwo = DemoTwo.getInstance();
                System.out.println(demoTwo);
            }).start();
        }

        new Integer(1);
    }

    /**
     * 测试饿汉式
     * @author dge
     * @date 2021-04-12 21:28
     */
    private static void testOne() {
        DemoOne instance = DemoOne.getInstance();
        DemoOne instance1 = DemoOne.getInstance();

        System.out.println(instance);
        System.out.println(instance1);
    }
}
