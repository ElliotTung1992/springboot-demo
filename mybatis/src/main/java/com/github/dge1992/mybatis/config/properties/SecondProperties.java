package com.github.dge1992.mybatis.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 *
 * @author 小眼睛带鱼
 * @date 2019-11-13 16:37:01
 * @desc 数据库数据源配置
 **/
@Data
public class SecondProperties {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private String validationQuery;

    private String[] dataSourceNames = {"dataSourceGuns", "dataSourceBiz"};

    public void config(DruidDataSource dataSource) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setValidationQuery(validationQuery);
    }
}
