package com.github.dge1992.fish.stream;

import com.github.dge1992.fish.domain.po.PersonPo;

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
        PersonPo personPo = new PersonPo();
        personPo.setName("dge");
        personPo.setAge(11);
        personPo.setMoney(new BigDecimal("1.1"));

        PersonPo personPo2 = new PersonPo();
        personPo2.setName("dge");
        personPo2.setAge(12);
        personPo2.setMoney(new BigDecimal("2.2"));

        PersonPo personPo3 = new PersonPo();
        personPo3.setName("dge");
        personPo3.setAge(11);
        personPo3.setMoney(new BigDecimal("2.2"));

        PersonPo personPo4 = new PersonPo();
        personPo4.setName("wlh");
        personPo4.setAge(12);
        personPo4.setMoney(new BigDecimal("1.1"));

        PersonPo personPo5 = new PersonPo();
        personPo5.setName("wlh");
        personPo5.setAge(11);
        personPo5.setMoney(new BigDecimal("2.2"));

        List<PersonPo> personPoList = new ArrayList<>();
        personPoList.add(personPo);
        personPoList.add(personPo2);
        personPoList.add(personPo3);
        personPoList.add(personPo4);
        personPoList.add(personPo5);

        // 用户信息 默认很多信息
        Map<String, BigDecimal> collect = personPoList.stream().collect(
                Collectors.groupingBy(a -> a.getName() + "#" + a.getAge(), Collectors.mapping(PersonPo::getMoney, BigDecimalCollector.toSum())));

        for (Map.Entry<String, BigDecimal> stringListEntry : collect.entrySet()) {
            System.out.println(stringListEntry.getKey());
            System.out.println(stringListEntry.getValue());
        }
    }

    private void testOne(List<PersonPo> personPoList){
        Map<String, Map<Integer, Map<BigDecimal, List<PersonPo>>>> collect = personPoList.stream().
                collect(Collectors.groupingBy(PersonPo::getName, Collectors.groupingBy(PersonPo::getAge, Collectors.groupingBy(PersonPo::getMoney))));
        collect.forEach((k, v) -> {
            System.out.println(k + "-" + v);
        });
    }
}
