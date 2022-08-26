package com.github.dge1992.fish.util;

import com.github.dge1992.fish.domain.Person;
import com.github.dge1992.fish.java.util.stream.BigDecimalCollector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dge
 * @version 1.0
 * @date 2021-09-13 09:25
 */
public class MapUtil {

    public static void main(String[] args) {
        MapUtil util = new MapUtil();
        util.caseOne();
    }

    private void caseOne() {
        List<Person> personList = new ArrayList<>();

        Person person = new Person();
        person.setName("dge");
        person.setAge(12);
        person.setMoney(new BigDecimal("1.02"));

        Person person2 = new Person();
        person2.setName("fnn");
        person2.setAge(26);
        person2.setMoney(new BigDecimal("2.54"));

        Person person3 = new Person();
        person3.setName("dge");
        person3.setAge(39);
        person3.setMoney(new BigDecimal("6.10"));

        personList.add(person);
        personList.add(person2);
        personList.add(person3);

        Map<String, List<Person>> map = personList.stream()
                .collect(Collectors.groupingBy(Person::getName));

        map.entrySet()
                .forEach(e -> {
                    System.out.println(e.getKey());
                    System.out.println(e.getValue());
                });

        map.replaceAll((k, v) -> {
            List<Person> list = new ArrayList<>();
            Person p = new Person();
            int sum = v.stream().mapToInt(Person::getAge)
                    .sum();
            p.setAge(sum);
            list.add(p);
            return list;
        });

        map.entrySet()
                .forEach(e -> {
                    System.out.println(e.getKey());
                    System.out.println(e.getValue());
                });

        System.out.println("=================");

        Map<String, Integer> collect = personList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.summingInt(Person::getAge)));

        collect.entrySet()
                .forEach(e -> {
                    System.out.println(e.getKey());
                    System.out.println(e.getValue());
                });

        System.out.println("=================");


        Map<String, BigDecimal> bigDecimalMap = personList.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.mapping(Person::getMoney, BigDecimalCollector.sum())));

        bigDecimalMap.entrySet()
                .forEach(e -> {
                    System.out.println(e.getKey());
                    System.out.println(e.getValue());
                });
    }
}
