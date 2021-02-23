package com.github.dge1992.fish.pattern.callback;

/**
 * @author dge
 * @version 1.0
 * @date 2021-02-23 13:43
 */
public class CallbackTest {

    public static void main(String[] args) {
        Callbacker callbacker = new Callbacker();
        callbacker.execute(new Callback() {
            @Override
            public void call() {
                System.out.println("嘻嘻");
            }
        });
    }
}
