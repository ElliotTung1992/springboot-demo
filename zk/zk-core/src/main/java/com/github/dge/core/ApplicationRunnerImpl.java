package com.github.dge.core;

import com.github.dge.core.cache.ValueBeanDefinitionContainer;
import com.github.dge.core.cache.ValueBeanDefinitionObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-13 1:48 PM
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Value("${name}")
    private String name;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(name);

        ValueBeanDefinitionContainer instance = ValueBeanDefinitionContainer.getInstance();
        Map<String, List<ValueBeanDefinitionObject>> cache = instance.cache;
        cache.forEach((k, v) -> {
            System.out.println("key:" + k);
            v.forEach(b -> System.out.println(b));
        });
    }
}
