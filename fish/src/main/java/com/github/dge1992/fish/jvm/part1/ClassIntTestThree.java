package com.github.dge1992.fish.jvm.part1;

/**
 * @author dge
 * @version 1.0
 * @date 2021-05-19 21:38
 */
public class ClassIntTestThree {

    private static int num = 10;

    ClassIntTestThree (){
        num = 20;
    }

    public static void main(String[] args) {
        System.out.println(ClassIntTestThree.num);

        ClassIntTestThree testThree = new ClassIntTestThree();

        System.out.println(ClassIntTestThree.num);
    }
}
