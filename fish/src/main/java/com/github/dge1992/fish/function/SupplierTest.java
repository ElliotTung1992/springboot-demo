package com.github.dge1992.fish.function;

import java.util.function.Supplier;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-01 10:09
 */
public class SupplierTest {

    public static void main(String[] args) {
        /*Supplier<String> supplier = new Supplier<>() {
            @Override
            public String get() {
                return "hello dge";
            }
        };*/

        Supplier<String> supplier = () -> "hello dge";

        System.out.println(supplier.get());
    }
}
