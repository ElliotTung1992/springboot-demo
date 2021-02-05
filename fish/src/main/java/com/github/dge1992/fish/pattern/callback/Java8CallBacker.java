package com.github.dge1992.fish.pattern.callback;

import java.util.function.Function;

/**
 * @author dge
 * @version 1.0
 * @date 2021-02-04 15:05
 */
public class Java8CallBacker {

    public void b(Function<String, String> function){
        System.out.println("哈哈");
        String str = function.apply("呵呵");
        System.out.println(str);
    }
}
