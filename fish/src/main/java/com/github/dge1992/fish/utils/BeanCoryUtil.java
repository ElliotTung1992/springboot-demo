package com.github.dge1992.fish.utils;

import com.github.dge1992.fish.domain.A;
import com.github.dge1992.fish.domain.B;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2021-10-14 11:43
 */
public class BeanCoryUtil {

    public static void main(String[] args) {
        // testOne();

        // testTwo();

        // testThree();
    }

    private static void testThree() {
        A first = new A();
        first.setName("demo");
        first.setIds(Arrays.asList(1, 2, 3));

        B second = Converter.INSTANCE.aToB(first);
        for (String each : second.getIds()) {
            System.out.println(each);
        }
    }

    private static void testTwo() {
        A first = new A();
        first.setName("demo");
        first.setIds(Arrays.asList(1, 2, 3));

        B second = new B();
        BeanCopier beanCopier = BeanCopier.create(A.class, B.class, false);
        beanCopier.copy(first, second, null);

        System.out.println(second);

        for (String id : second.getIds()) {
            System.out.println(id);
        }
    }

    private static void testOne() {
        A first = new A();
        first.setName("demo");
        first.setIds(Arrays.asList(1, 2, 3));

        B second = new B();
        BeanUtils.copyProperties(first, second);
        // 类型转换异常
        for (String each : second.getIds()) {
            System.out.println(each);
        }
    }
}