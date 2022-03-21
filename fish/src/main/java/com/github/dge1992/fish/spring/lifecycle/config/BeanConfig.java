package com.github.dge1992.fish.spring.lifecycle.config;

import com.github.dge1992.fish.spring.lifecycle.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-21 3:50 下午
 */
@Configuration
public class BeanConfig {

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public Person person(){
        return new Person();
    }
}
