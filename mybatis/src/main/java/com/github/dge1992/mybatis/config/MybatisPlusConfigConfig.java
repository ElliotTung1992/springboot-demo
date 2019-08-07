package com.github.dge1992.mybatis.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/8/7
 **/
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)//开启事务
@MapperScan(value = "com.github.dge1992.mybatis.mapper")
public class MybatisPlusConfigConfig {

    /**
     * @author dongganen
     * @date 2019/8/7
     * @desc: 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
