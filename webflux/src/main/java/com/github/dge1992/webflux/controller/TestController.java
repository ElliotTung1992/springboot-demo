package com.github.dge1992.webflux.controller;

import com.github.dge1992.webflux.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author dge
 * @version 1.0
 * @date 2021-03-09 14:50
 */
@RequestMapping("/test")
@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/caseOne")
    private Mono<List<User>> caseOne() {
        logger.info("caseOne start.");
        Mono<List<User>> result = Mono.fromSupplier(() -> returnUserList());
        logger.info("caseOne end.");
        return result;
    }

    @GetMapping(value = "/caseTwo", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<User> caseTwo() {
        Flux<User> result = Flux
                .fromStream(IntStream.range(1, 5).mapToObj(i -> {
                    User user = new User();
                    user.setId(Long.valueOf(i));
                    user.setName("dge".concat(String.valueOf(i)));
                    user.setAge(10 + i);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    return user;
                }));
        return result;
    }

    private List<User> returnUserList(){
        List<User> userList = new ArrayList<>();
        IntStream.range(0, 5).forEach(e -> {
            User user = new User();
            user.setId(Long.valueOf(e));
            user.setName("dge".concat(String.valueOf(e)));
            user.setAge(10 + e);
            userList.add(user);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        return userList;
    }
}
