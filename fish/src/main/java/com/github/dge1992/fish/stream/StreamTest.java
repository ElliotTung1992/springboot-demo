package com.github.dge1992.fish.stream;

import com.github.dge1992.fish.domain.po.PersonPo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author dge
 * @version 1.0
 * @date 2021-03-30 11:18
 */
public class StreamTest {

    public static void main(String[] args) {
        StreamTest streamTest = new StreamTest();
        streamTest.listAddAllTwo();
    }

    private void listAddAllTwo(){
        List<Integer> collect = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        List<Integer> collect1 = IntStream.range(10, 20).boxed().collect(Collectors.toList());

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(collect);
        lists.add(collect1);
        List<Integer> resultList = lists.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(resultList);
    }

    /**
     * List<List<String>> -> List<String>
     *
     * @return void
     * @author dge
     * @date 2022/3/24 7:50 下午
     */
    private static void listAddAll(){
        List<Integer> collect = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        List<Integer> collect1 = IntStream.range(10, 20).boxed().collect(Collectors.toList());

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(collect);
        lists.add(collect1);
        ArrayList<Integer> collect2 = lists.stream().collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
        System.out.println(collect2);
    }

    /**
     * 区分peek和map
     *
     * @author dge
     * @date 2021-03-30 11:25
     */
    private static void distinguishPeekAndMap() {
        List<PersonPo> list = new ArrayList<>();
        PersonPo personPoOne = new PersonPo();
        personPoOne.setName("dge");
        personPoOne.setAge(12);

        PersonPo personPoTwo = new PersonPo();
        personPoTwo.setName("fnn");
        personPoTwo.setAge(13);

        list.add(personPoOne);
        list.add(personPoTwo);
        // peek
        List<PersonPo> collect = list.stream().peek(e -> e.setAge(e.getAge() + 1)).collect(Collectors.toList());
        System.out.println(collect);

        // map
        List<Integer> collect1 = list.stream().map(e -> e.getAge() + 1).collect(Collectors.toList());
        System.out.println(collect1);
    }

    private static void testTwo(List<PersonPo> list){
        Map<Integer, List<PersonPo>> map = list.stream().collect(Collectors.groupingBy(PersonPo::getAge));
    }
}
