package com.github.dge1992.zookeeper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dge
 * @version 1.0
 * @date 2021-11-30 10:20 PM
 */
@Data
@Component
@ConfigurationProperties(prefix = "curator")
public class WrapperZk {

    private int retryCount;

    private int elapsedTimeMs;

    private String connectString;

    private int sessionTimeoutMs;

    private int connectionTimeoutMs;
}
