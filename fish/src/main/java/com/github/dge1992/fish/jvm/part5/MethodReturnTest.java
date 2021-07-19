package com.github.dge1992.fish.jvm.part5;

import java.util.Date;

/**
 * @author dge
 * @version 1.0
 * @date 2021-06-21 21:45
 */
public class MethodReturnTest {

    public int returnInteger(){
        return 5;
    }

    public short returnShort(){
        return 1;
    }

    public byte returnByte(){
        return 'a';
    }

    public boolean returnboolean(){
        return Boolean.TRUE;
    }

    public long returnLong(){
        return 11L;
    }

    public double returnDouble(){
        return 22.33;
    }

    public char returnChar(){
        return 'a';
    }

    public String returnString(){
        return "";
    }

    public Date returnDate(){
        return null;
    }

    public void returnVoid(){

    }

    public void method1(){
        try {
            int a =10;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
