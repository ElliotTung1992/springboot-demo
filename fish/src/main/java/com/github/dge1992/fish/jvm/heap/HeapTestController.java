package com.github.dge1992.fish.jvm.heap;

import com.github.dge1992.fish.domain.po.PersonPo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HeapTestController {

    @GetMapping("/testOutOfMemory")
    public void testOutOfMemory(){
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
