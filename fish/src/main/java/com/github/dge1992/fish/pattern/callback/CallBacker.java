package com.github.dge1992.fish.pattern.callback;

/**
 * @author dge
 * @version 1.0
 * @date 2021-02-04 14:5*/
public class CallBacker {

    public void b(CallBackUnit callBackUnit){
        System.out.println("哈哈");
        String str = callBackUnit.callback("呵呵");
        System.out.println(str);
    }
}
