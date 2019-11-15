package com.github.dge1992.mybatis.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.dge1992.mybatis.config.properties.BasicProperties;
import com.github.dge1992.mybatis.config.properties.SecondProperties;
import com.github.dge1992.mybatis.mutidatasource.DynamicDataSource;
import com.github.dge1992.mybatis.mutidatasource.aop.MultiSourceAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author 小眼睛带鱼
 * @date 2019-11-15 10:47
 * @desc 多数据源配置
 */
@EnableTransactionManagement(proxyTargetClass = true)//开启事务
@ConditionalOnProperty(prefix = "spring.multi-datasource", name = "open", havingValue = "true")
@Configuration
public class MultiDataSourceConfig {

    private static HashMap<Object, Object> hashMap = null;

    @Bean
    @ConfigurationProperties(prefix = "spring.second.datasource")
    public SecondProperties secondProperties(){
        return new SecondProperties();
    }

    @Bean
    public MultiSourceAspect multiSourceAspect(){
        return new MultiSourceAspect();
    }

    public DruidDataSource dataSource(BasicProperties basicProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        basicProperties.config(dataSource);
        return dataSource;
    }

    private DruidDataSource bizDataSource(BasicProperties basicProperties, SecondProperties secondProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        basicProperties.config(dataSource);
        secondProperties.config(dataSource);
        return dataSource;
    }

    @Bean
    public DynamicDataSource multiDataSource(BasicProperties basicProperties, SecondProperties secondProperties) {

        DruidDataSource dataSourceGuns = dataSource(basicProperties);
        DruidDataSource bizDataSource = bizDataSource(basicProperties, secondProperties);

        try {
            dataSourceGuns.init();
            bizDataSource.init();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        hashMap = new HashMap<>();
        hashMap.put("basic", dataSourceGuns);
        hashMap.put("second", bizDataSource);
        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceGuns);
        return dynamicDataSource;
    }

    public static Object getDataSource(String key){
        return hashMap.get(key);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DynamicDataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
