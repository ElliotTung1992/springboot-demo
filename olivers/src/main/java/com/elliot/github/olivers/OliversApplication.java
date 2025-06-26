package com.elliot.github.olivers;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.elliot.github.olivers.mapper")
@SpringBootApplication
public class OliversApplication {

    public static void main(String[] args) {
        SpringApplication.run(OliversApplication.class, args);
    }

}
