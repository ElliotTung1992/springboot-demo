package com.github.dge1992.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小眼睛带鱼
 * @date 2019-12-05 09:44
 * @desc
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Bean
    public RedissonClient getRedisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        return Redisson.create(config);
    }

}
