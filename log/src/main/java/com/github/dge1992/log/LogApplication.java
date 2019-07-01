package com.github.dge1992.log;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class LogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
        log.trace("TRACE级别的信息");
        log.debug("DEBUG级别的信息");
        log.info("INFO级别的信息");
        log.warn("WARN级别的信息");
        log.error("ERROR级别的信息");
        log.fatal("FATAL级别的信息");
    }

}
