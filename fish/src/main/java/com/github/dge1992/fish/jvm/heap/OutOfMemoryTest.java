package com.github.dge1992.fish.jvm.heap;

import com.github.dge1992.fish.domain.po.PersonPo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 内存溢出
 */
public class OutOfMemoryTest {

    public static void main(String[] args) {
        // List<Object> list = new ArrayList<>();
        ThreadLocal<List<PersonPo>> threadLocal = new ThreadLocal<>();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        while (true) {
            executorService.submit(() -> {
                List<PersonPo> personPoList = new ArrayList<>();
                for (int i = 0; i < 1000000; i++) {
                    PersonPo personPo = new PersonPo();
                    personPo.setName("Mike" + i);
                    personPo.setAge(i);
                    personPo.setGender(1);
                    personPoList.add(personPo);
                }
                threadLocal.set(personPoList);
            });
        }
    }
}
