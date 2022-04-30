package com.github.dge1992.zookeeper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author dge
 * @version 1.0
 * @date 2021-12-08 9:46 AM
 */
@Component
public class SubConfiguration {

    @Value("${name}")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
