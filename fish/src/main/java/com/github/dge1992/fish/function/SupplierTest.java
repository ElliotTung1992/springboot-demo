package com.github.dge1992.fish.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-01 10:09
 */
public class SupplierTest {

    public static void main(String[] args) {

        Supplier<String> supplier = () -> "hello dge";
        System.out.println(supplier.get());

        SupplierTest test = new SupplierTest();
        test.caseOne();
    }

    private void caseOne() {
        Supplier<List<String>> supplier = ArrayList::new;
        List<String> strings = supplier.get();
        System.out.println(strings);
    }
}
