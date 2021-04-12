package com.github.dge1992.fish.pattern.single;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-12 21:25
 * 饿汉式
 *
 * 缺点：当单例对象存在大单位属性时，系统启动就创建对象对性能有消耗
 * 有点：没有多线程问题
 */
public class DemoOne {

    private DemoOne(){}

    private static DemoOne demoOne = new DemoOne();

    public static DemoOne getInstance(){
        return demoOne;
    }
}
