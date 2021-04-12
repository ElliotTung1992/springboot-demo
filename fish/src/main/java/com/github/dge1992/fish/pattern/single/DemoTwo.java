package com.github.dge1992.fish.pattern.single;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-12 21:32
 * 懒汉式 DCL懒汉式
 */
public class DemoTwo {

    private static Boolean dge = false;

    private DemoTwo(){
        /*
        可以防止反射破坏
        synchronized (DemoTwo.class){
            if(demoTwo != null){
                throw new RuntimeException("不要试图通过反射破坏单例模式");
            }
        }*/

        //通过红绿灯防止反射破坏
        synchronized (DemoTwo.class){
            if(!dge){
                dge = true;
            }else {
                throw new RuntimeException("不要试图通过反射破坏单例模式");
            }
        }

        System.out.println(Thread.currentThread().getName());
    }

    private volatile static DemoTwo demoTwo;

    public static DemoTwo getInstance(){
        if(demoTwo == null){
            synchronized (DemoTwo.class) {
                if(demoTwo == null){
                    demoTwo = new DemoTwo();
                    /**
                     * 此操作不是原子性
                     * demoTwo = new DemoTwo();
                     * 1：分配内存空间
                     * 2：执行构造方法
                     * 3：对象指向这个空间
                     *
                     * 指令重排
                     * 线程A 132
                     * 线程B 有可能等到一个null对象
                     */
                }
            }
        }
        return demoTwo;
    }
}
