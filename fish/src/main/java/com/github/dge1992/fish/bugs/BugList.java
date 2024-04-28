package com.github.dge1992.fish.bugs;

import org.w3c.dom.UserDataHandler;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-13 9:19 下午
 * 记一次线上bug：
 * 获取枚举Class的时候注意
 * 使用getDeclaringClass
 * 而不是getClass
 */
public class BugList {

    public static void main(String[] args) {
        System.out.println(State.OFF);
        System.out.println(State.ON);
        System.out.println(State.OFF.getName());
        System.out.println(State.ON.getName());
        // class com.github.dge1992.fish.bugs.State$2
        System.out.println(State.OFF.getClass());
        // class com.github.dge1992.fish.bugs.State
        System.out.println(State.ON.getDeclaringClass());
    }
}


