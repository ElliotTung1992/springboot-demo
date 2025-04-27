package com.github.dge1992.fish.jvm.heap;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.dge1992.fish.domain.po.PersonPo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OutOfMemoryTest2 {

    public static void main(String[] args) {
        Cache<Object, List<PersonPo>> cache = Caffeine.newBuilder()
                .maximumSize(Integer.MAX_VALUE)
                .weakValues()
                .build();
        while (true) {
            List<PersonPo> personPoList = new ArrayList<>();
            for (int i = 0; i < 1000000; i++) {
                PersonPo personPo = new PersonPo();
                personPo.setName("Mike" + i);
                personPo.setAge(i);
                personPo.setGender(1);
                personPoList.add(personPo);
            }
            cache.put(UUID.randomUUID(), personPoList);
            System.out.println(cache.estimatedSize());
        }
    }
}
