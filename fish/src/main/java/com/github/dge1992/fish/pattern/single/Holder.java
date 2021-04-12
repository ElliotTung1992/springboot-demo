package com.github.dge1992.fish.pattern.single;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-12 21:58
 * 静态内部类
 */
public class Holder {

    private Holder(){
        System.out.println(Thread.currentThread().getName());
    }

    public static Holder getInstance(){
        return InnerClass.HOLDER;
    }

    public static class InnerClass{
        private static Holder HOLDER = new Holder();
    }
}
