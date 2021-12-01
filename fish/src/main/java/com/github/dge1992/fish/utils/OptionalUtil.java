package com.github.dge1992.fish.utils;

import com.github.dge1992.fish.domain.Person;

import java.util.Optional;

/**
 * @author dge
 * @version 1.0
 * @date 2021-09-29 14:57
 */
public class OptionalUtil {

    public static void main(String[] args) {

        // createOptional();

        // orElse();

        // map();

        // ifPresent();
    }

    private static void ifPresent() {
        Person person = new Person();
        person.setName("dge");
        Optional.ofNullable(person).ifPresent(p -> {
            System.out.println(p.getName());
        });
        Person p = Optional.ofNullable(person)
                .filter(pe -> pe.getName().length() > 3).orElseGet(() -> {
                    Person person1 = new Person();
                    person1.setName("成吉思汗");
                    return person1;
                });
        System.out.println(p);
    }

    private static void map() {
        //Person person = new Person();
        Person person = null;
        //person.setName("dge");
        String name = Optional.ofNullable(person).map(p -> p.getName()).orElse("fnn");
        System.out.println(name);

        String name2 = Optional.ofNullable(person).flatMap(p -> Optional.ofNullable(p.getName())).orElse("tf");
        System.out.println(name2);
    }

    /**
     * orElse操作
     */
    private static void orElse() {
        Person person = null;
        Person person2 = Optional.ofNullable(person).orElse(createPerson());
        System.out.println(person2);
        Optional.ofNullable(person)
                .orElseGet(() -> createPerson());

        Optional.ofNullable(person).orElseThrow(() -> new RuntimeException());
    }

    /**
     * 创建Optional的方式
     */
    private static void createOptional() {
        String str = "dge";
        String strNull = null;
        Optional.of(str);
        Optional.ofNullable(strNull);
    }

    private static Person createPerson(){
        System.out.println("hello world");
        return new Person();
    }
}
