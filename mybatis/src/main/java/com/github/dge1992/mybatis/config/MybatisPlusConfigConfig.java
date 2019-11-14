package com.github.dge1992.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.github.dge1992.mybatis.config.properties.DruidProperties;
import com.github.dge1992.mybatis.spring.transaction.LogTransactionInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.Ordered;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.SpringTransactionAnnotationParser;
import org.springframework.transaction.config.TransactionManagementConfigUtils;
import org.springframework.transaction.interceptor.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author 小眼睛带鱼
 * @Description 数据源配置
 * @Date 2019/8/7
 **/
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)//开启事务
@MapperScan(value = "com.github.dge1992.mybatis.mapper")
public class MybatisPlusConfigConfig {

    @Autowired
    DruidProperties druidProperties;

    /**
     * druid数据库连接池
     */
    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * SQL执行效率插件
     */
    //@Bean
    //@Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //maxTime SQL 执行最大时长，超过自动停止运行
        performanceInterceptor.setMaxTime(100l);
        //format SQL SQL是否格式化，默认false
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     * @author dongganen
     * @date 2019/8/7
     * @desc: 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击SQL阻断解析器、加入解析链
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

    /**
     * @author dongganen
     * @date 2019/8/13
     * @desc: 乐观锁插件
     *        仅支持 updateById(id) 与 update(entity, wrapper) 方法
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
