package com.github.dge1992.fish.jvm.part5;

import java.util.Date;

/**
 * @author dge
 * @version 1.0
 * @date 2021-06-01 10:25
 */
public class StackFrameTest {

    public static void main(String[] args) {
        StackFrameTest test = new StackFrameTest();
        int num = 10;
        test.method1();
    }

    public static Integer testStatic(Double d){
        StackFrameTest test = new StackFrameTest();
        Date date = new Date();
        int num = 10;
        test.method1();
        return num;
    }

    public void method4(){
        int i = 10;
        long l = 11L;
        double d = 2.2;
        String str = "abc";
    }

    public void method5(){
        int i = 10;
        {
            int j = 20;
            j = i + 10;
        }
        int k = i + 20;
    }

    public void method6(){
        /**
         * 变量根据类型可以分为2类 1：基础类型  2：引用类型
         * 变量根据位置可以分为2类 1：成员变量   1.1 类变量 1.2 实例变量
         *                    2：局部变量
         */
        /*int num;
        System.out.println(num);*/
    }

    public void method1(){
        System.out.println("method1执行开始");
        method2();
        System.out.println("method1执行结束");
        return;
    }

    public int method2() {
        System.out.println("method2执行开始");
        int i = 10;
        int j = (int) (method3() + i);
        System.out.println("method2即将执行结束");
        return j;
    }

    public double method3() {
        System.out.println("method3执行开始");
        double d = 22.0;
        System.out.println("method3即将执行结束");
        return d;
    }
}
