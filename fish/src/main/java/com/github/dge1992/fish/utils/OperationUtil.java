package com.github.dge1992.fish.utils;

/**
 * @author dge
 * @version 1.0
 * @date 2021-07-02 11:33
 * Java运算符工具类
 */
public class OperationUtil {

    /**
     * 十进制转二进制
     * @param l long
     * @return java.lang.String
     */
    public static String toBinaryString(Long l){
        return Long.toBinaryString(l);
    }

    public static void main(String[] args) {
        /*System.out.println(toBinaryString(609751022365323264L));
        System.out.println(toBinaryString(609751026563821568L));
        System.out.println(toBinaryString(608302130260094976L));
        System.out.println(toBinaryString(608302134475370496L));*/
    }
}
