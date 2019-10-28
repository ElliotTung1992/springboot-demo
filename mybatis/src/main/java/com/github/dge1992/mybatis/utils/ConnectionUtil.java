package com.github.dge1992.mybatis.utils;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionUtil {

    public static void run(SqlSessionTemplate template, String sql) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rt = null;
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionUtils.getSqlSession(template.getSqlSessionFactory(), template.getExecutorType(), template.getPersistenceExceptionTranslator());
            connection = sqlSession.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            rt = statement.getResultSet();
            while (rt.next()){
                System.out.println("age:" + rt.getInt("age"));
            }
            // TODO
        } catch (Exception e) {
            // TODO
        } finally {
            SqlSessionUtils.closeSqlSession(sqlSession, template.getSqlSessionFactory());
        }
    }
}
