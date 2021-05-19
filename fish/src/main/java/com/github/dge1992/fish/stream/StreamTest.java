package com.github.dge1992.fish.stream;

import com.github.dge1992.fish.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dge
 * @version 1.0
 * @date 2021-03-30 11:18
 */
public class StreamTest {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person personOne = new Person();
        personOne.setName("dge");
        personOne.setAge(12);

        Person personTwo = new Person();
        personTwo.setName("fnn");
        personTwo.setAge(13);

        list.add(personOne);
        list.add(personTwo);

        testOne(list);
    }

    /**
     * 区分peek和map
     * @param list list
     * @author dge
     * @date 2021-03-30 11:25
     */
    private static void testOne(List<Person> list) {
        // peek
        List<Person> collect = list.stream().peek(e -> e.setAge(e.getAge() + 1)).collect(Collectors.toList());
        System.out.println(collect);

        // map
        List<Integer> collect1 = list.stream().map(e -> e.getAge() + 1).collect(Collectors.toList());
        System.out.println(collect1);
    }

    private static void testTwo(List<Person> list){
        Map<Integer, List<Person>> map = list.stream().collect(Collectors.groupingBy(Person::getAge));
    }
}
