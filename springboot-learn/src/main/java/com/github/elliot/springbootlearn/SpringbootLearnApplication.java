package com.github.elliot.springbootlearn;

import com.github.elliot.springbootlearn.annotation.EnableCatch;
import com.github.elliot.springbootlearn.annotation.MapperBeanScan;
import com.github.elliot.springbootlearn.enums.CatchTypeEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperBeanScan(basePackages = "com.github.elliot")
@EnableCatch(catchType = CatchTypeEnum.REDIS)
@SpringBootApplication
public class SpringbootLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLearnApplication.class, args);
    }

}
