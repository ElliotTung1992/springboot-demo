package com.github.dge1992.fish.stream;

import com.github.dge1992.fish.domain.Person;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-20 9:57 AM
 */
public class GroupByTest {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("dge");
        person.setAge(11);
        person.setMoney(new BigDecimal("1.1"));

        Person person2 = new Person();
        person2.setName("dge");
        person2.setAge(12);
        person2.setMoney(new BigDecimal("2.2"));

        Person person3 = new Person();
        person3.setName("dge");
        person3.setAge(11);
        person3.setMoney(new BigDecimal("2.2"));

        Person person4 = new Person();
        person4.setName("wlh");
        person4.setAge(12);
        person4.setMoney(new BigDecimal("1.1"));

        Person person5 = new Person();
        person5.setName("wlh");
        person5.setAge(11);
        person5.setMoney(new BigDecimal("2.2"));

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);

        // 用户信息 默认很多信息
        Map<String, BigDecimal> collect = personList.stream().collect(
                Collectors.groupingBy(a -> a.getName() + "#" + a.getAge(), Collectors.mapping(Person::getMoney, BigDecimalCollector.toSum())));

        for (Map.Entry<String, BigDecimal> stringListEntry : collect.entrySet()) {
            System.out.println(stringListEntry.getKey());
            System.out.println(stringListEntry.getValue());
        }
    }

    private void testOne(List<Person> personList){
        Map<String, Map<Integer, Map<BigDecimal, List<Person>>>> collect = personList.stream().
                collect(Collectors.groupingBy(Person::getName, Collectors.groupingBy(Person::getAge, Collectors.groupingBy(Person::getMoney))));
        collect.forEach((k, v) -> {
            System.out.println(k + "-" + v);
        });
    }
}
