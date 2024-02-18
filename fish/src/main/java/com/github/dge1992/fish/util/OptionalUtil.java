package com.github.dge1992.fish.util;

import com.github.dge1992.fish.domain.po.PersonPo;

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
        PersonPo personPo = new PersonPo();
        personPo.setName("dge");
        Optional.ofNullable(personPo).ifPresent(p -> {
            System.out.println(p.getName());
        });
        PersonPo p = Optional.ofNullable(personPo)
                .filter(pe -> pe.getName().length() > 3).orElseGet(() -> {
                    PersonPo personPo1 = new PersonPo();
                    personPo1.setName("成吉思汗");
                    return personPo1;
                });
        System.out.println(p);
    }

    private static void map() {
        //Person person = new Person();
        PersonPo personPo = null;
        //person.setName("dge");
        String name = Optional.ofNullable(personPo).map(p -> p.getName()).orElse("fnn");
        System.out.println(name);

        String name2 = Optional.ofNullable(personPo).flatMap(p -> Optional.ofNullable(p.getName())).orElse("tf");
        System.out.println(name2);
    }

    /**
     * orElse操作
     */
    private static void orElse() {
        PersonPo personPo = null;
        PersonPo personPo2 = Optional.ofNullable(personPo).orElse(createPerson());
        System.out.println(personPo2);
        Optional.ofNullable(personPo)
                .orElseGet(() -> createPerson());

        Optional.ofNullable(personPo).orElseThrow(() -> new RuntimeException());
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

    private static PersonPo createPerson(){
        System.out.println("hello world");
        return new PersonPo();
    }
}
