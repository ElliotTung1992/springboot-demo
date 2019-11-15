package com.github.dge1992.mybatis.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.dge1992.mybatis.config.properties.BasicProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 小眼睛带鱼
 * @date 2019-11-15 17:06
 * @desc 单数据源配置
 */
@EnableTransactionManagement(proxyTargetClass = true)//开启事务
@ConditionalOnProperty(prefix = "spring.multi-datasource", name = "open", havingValue = "false", matchIfMissing = true)
@Configuration
public class SingleDataSourceConfig {

    @Bean
    public DruidDataSource dataSource(BasicProperties basicProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        basicProperties.config(dataSource);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DruidDataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
