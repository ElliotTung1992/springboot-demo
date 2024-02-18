package com.github.dge1992.fish.util;

import com.github.dge1992.fish.domain.po.PersonPo;
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

        /*new Thread(() -> {
            System.out.println("hello world");
        }).start();*/

        /*MapUtil util = new MapUtil();
        util.caseOne();*/

        /*Integer a2 = new Integer(127);
        Integer b2 = 127;
        System.out.println(a2 == b2);*/

        /*float a = 3.14;
        System.out.println(a);*/
    }

    private void caseOne() {
        List<PersonPo> personPoList = new ArrayList<>();

        PersonPo personPo = new PersonPo();
        personPo.setName("dge");
        personPo.setAge(12);
        personPo.setMoney(new BigDecimal("1.02"));

        PersonPo personPo2 = new PersonPo();
        personPo2.setName("fnn");
        personPo2.setAge(26);
        personPo2.setMoney(new BigDecimal("2.54"));

        PersonPo personPo3 = new PersonPo();
        personPo3.setName("dge");
        personPo3.setAge(39);
        personPo3.setMoney(new BigDecimal("6.10"));

        personPoList.add(personPo);
        personPoList.add(personPo2);
        personPoList.add(personPo3);

        Map<String, List<PersonPo>> map = personPoList.stream()
                .collect(Collectors.groupingBy(PersonPo::getName));

        map.entrySet()
                .forEach(e -> {
                    System.out.println(e.getKey());
                    System.out.println(e.getValue());
                });

        map.replaceAll((k, v) -> {
            List<PersonPo> list = new ArrayList<>();
            PersonPo p = new PersonPo();
            int sum = v.stream().mapToInt(PersonPo::getAge)
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

        Map<String, Integer> collect = personPoList.stream()
                .collect(Collectors.groupingBy(PersonPo::getName, Collectors.summingInt(PersonPo::getAge)));

        collect.entrySet()
                .forEach(e -> {
                    System.out.println(e.getKey());
                    System.out.println(e.getValue());
                });

        System.out.println("=================");


        Map<String, BigDecimal> bigDecimalMap = personPoList.stream()
                .collect(Collectors.groupingBy(PersonPo::getName,
                        Collectors.mapping(PersonPo::getMoney, BigDecimalCollector.sum())));

        bigDecimalMap.entrySet()
                .forEach(e -> {
                    System.out.println(e.getKey());
                    System.out.println(e.getValue());
                });
    }
}
