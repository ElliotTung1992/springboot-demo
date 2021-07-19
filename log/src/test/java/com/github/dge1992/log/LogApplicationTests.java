package com.github.dge1992.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(LogApplicationTests.class);

    public static final Logger ERROR_LOGGER = LoggerFactory.getLogger("ERROR_LOG");

    @Test
    public void contextLoads() {
    }

    @Test
    public void testOne(){
        log.error("i am a error log");
        logger.warn("i am a warn log");

        ERROR_LOGGER.error("哈哈");


        new Thread(() -> {
            logger.error("呵呵");
        }).start();
    }

}
