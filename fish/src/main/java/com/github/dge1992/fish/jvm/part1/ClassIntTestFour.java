package com.github.dge1992.fish.jvm.part1;

/**
 * @author dge
 * @version 1.0
 * @date 2021-05-19 21:45
 */
public class ClassIntTestFour {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("开始");
            AAA aaa = new AAA();
            System.out.println("结束");
        };

        new Thread(runnable, "线程1").start();
        new Thread(runnable, "线程2").start();
    }
}

class AAA{
    static {
        if(true){
            System.out.println(Thread.currentThread().getName() + "进入同步代码块");
            /*while (true){

            }*/
        }
    }
}
