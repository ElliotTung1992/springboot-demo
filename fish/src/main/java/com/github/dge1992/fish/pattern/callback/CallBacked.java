package com.github.dge1992.fish.pattern.callback;

/**
 * @author dge
 * @version 1.0
 * @date 2021-02-04 14:59
 */
public class CallBacked {

    public static void main(String[] args) {
        CallBacker callBacker = new CallBacker();
        callBacker.b(new CallBackUnit() {
            @Override
            public String callback(String param) {
                System.out.println(" I am call back and param is " + param);
                return "嘻嘻";
            }
        });
    }
}
