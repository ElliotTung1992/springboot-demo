package com.github.dge1992.fish.domain;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-27 11:24
 */
public class DomeObject {

    public int publicVar = 1;
    protected int protectedVar = 2;
    private int privateVar = 3;
    public int[] arrayData = new int[]{1, 2, 3};

    public DomeObject() {
    }

    @Override
    public String toString() {
        return "Demo{" +
                "publicVar=" + publicVar +
                ", protectedVar=" + protectedVar +
                ", privateVar=" + privateVar +
                ", arrayData=" + Arrays.toString(arrayData) +
                '}';
    }
}
