package com.github.dge1992.fish.utils;

/**
 * @author dge
 * @version 1.0
 * @date 2021-09-22 15:51
 */
public class BitMapUtil {

    public static void main(String[] args) {
        int n = 15;
        byte[] b = new byte[getIndex(n) + 1];
        add(b, 14);
        System.out.println(b[1]);
        System.out.println(contains(b, 14));
        clear(b, 14);
        System.out.println(b[1]);
        System.out.println(contains(b, 14));
    }

    private static int getIndex(Integer n) {
        return n >>> 3;
    }

    private static int getPosition(int num){
        return num & 0x07;
    }

    private static void add(byte[] b, int num){
        b[getIndex(num)] |= 1 << getPosition(num);
    }

    private static boolean contains(byte[] b, int num){
        return (b[getIndex(num)] & 1 << getPosition(num)) != 0;
    }

    private static void clear(byte[] b, int num){
        b[getIndex(num)] &= ~(1 << getPosition(num));
    }
}
