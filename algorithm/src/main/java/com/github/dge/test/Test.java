package com.github.dge.test;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.addRowData(null);
    }

    public void addRowData(List<Test> dynamicData) {
        if (!CollectionUtils.isEmpty(dynamicData)) {
            System.out.println("aa");
        }
    }
}
