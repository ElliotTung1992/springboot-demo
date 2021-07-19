package com.github.dge1992.fish.jvm.part5;

/**
 * @author dge
 * @version 1.0
 * @date 2021-06-09 11:26
 */
public class OperandStack {

    public int testOne(){
        int i = 8;
        char j = 10;
        int k = i + j;

        return k;
    }

    public void testTwo(){
        int i = testOne();
        int j = i + 10;
    }


}
