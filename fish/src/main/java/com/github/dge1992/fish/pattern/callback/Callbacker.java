package com.github.dge1992.fish.pattern.callback;

/**
 * @author dge
 * @version 1.0
 * @date 2021-02-23 13:42
 */
public class Callbacker {

    public void execute(Callback callback){
        System.out.println("哈哈");
        callback.call();
        System.out.println("呵呵");
    }
}
