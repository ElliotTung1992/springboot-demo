package com.github.dge1992.fish.spring.aop;

public class Bird implements Fly {

    @Override
    public void fly() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("小鸟飞翔");
    }
}
