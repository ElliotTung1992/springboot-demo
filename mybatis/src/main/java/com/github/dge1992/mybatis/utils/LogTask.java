package com.github.dge1992.mybatis.utils;


import com.ecer.kafka.connect.oracle.OracleSourceTask2;
import com.ecer.kafka.connect.oracle.models.BusinessLog;
import org.apache.ibatis.session.SqlSession;
import org.apache.kafka.connect.source.SourceRecord;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;

import java.sql.Connection;
import java.util.List;

public class LogTask {

    public static void run(SqlSessionTemplate template, BusinessLog log) {

        Connection connection;
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionUtils.getSqlSession(template.getSqlSessionFactory(), template.getExecutorType(), template.getPersistenceExceptionTranslator());
            OracleSourceTask2 oracleSourceTask = new OracleSourceTask2();
            connection = sqlSession.getConnection();
            oracleSourceTask.start(connection, log);
            List<SourceRecord> sourceRecords = oracleSourceTask.poll();
            // TODO 对结果集进行操作
            for (SourceRecord sourceRecord: sourceRecords) {
                System.out.println(sourceRecord);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SqlSessionUtils.closeSqlSession(sqlSession, template.getSqlSessionFactory());
        }
    }
}
