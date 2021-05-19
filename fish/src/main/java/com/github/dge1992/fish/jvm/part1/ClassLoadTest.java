package com.github.dge1992.fish.jvm.part1;

/**
 * @author dge
 * @version 1.0
 * @date 2021-05-19 21:50
 * 类加载器
 */
public class ClassLoadTest {

    public static void main(String[] args) {

        // 系统类加载器
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(appClassLoader);

        // 平台类加载器
        ClassLoader extClassLoader = appClassLoader.getParent();
        System.out.println(extClassLoader);

        // 引导类加载器
        ClassLoader bootStrapClassLoader = extClassLoader.getParent();
        System.out.println(bootStrapClassLoader);

        ClassLoader classLoader = ClassLoadTest.class.getClassLoader();
        System.out.println(classLoader);

        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);


        System.out.println("=======================");

    }
}
