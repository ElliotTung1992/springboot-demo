package com.github.dge1992.fish.java.sun.misc;

import com.github.dge1992.fish.domain.Person;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-09 10:54 上午
 */
public class UnsafeTest {

    public static void main(String[] args) throws IllegalAccessException, InterruptedException {

        // TODO study dump

        // Unsafe使用了单例模式，并且限制只能主类加载器加载的类才能使用

        // 通过反射绕开限制
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        Person person = new Person();
        person.setAge(18);

        long address = unsafe.getAddress(18);
        System.out.println(address);

        int anInt = unsafe.getInt(person, 12);
        System.out.println(anInt);

        TimeUnit.SECONDS.sleep(5);
    }
}
