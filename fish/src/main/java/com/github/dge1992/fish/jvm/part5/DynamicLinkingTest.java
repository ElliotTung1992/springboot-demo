package com.github.dge1992.fish.jvm.part5;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2021-06-09 15:40
 */
public class DynamicLinkingTest {

    public DynamicLinkingTest(){

    }

    public DynamicLinkingTest(String name){
        this();
    }

    int i = 10;

    public void methodA(){
        System.out.println("methodA");
    }

    public void methodB(){
        System.out.println("methodB");

        methodA();

        i++;
    }

    public void methodC(){
        List<String> list = new ArrayList<>();
        list.stream().forEach(System.out::println);
    }
}
