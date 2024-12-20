package com.example.flinkcdc.mysql;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

public class FlinkCDCSql {

    public static void main(String[] args) {
        // 获取Flink执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(env);

        // 使用Table Api进行建表
        tableEnvironment.executeSql("CREATE TABLE t1 (\n" +
                "  id int primary key not enforced,\n" +
                "  name STRING\n" +
                ") WITH (\n" +
                "'connector' = 'mysql-cdc',\n" +
                "'hostname' = '10.211.55.2',\n" +
                "'port' = '3306',\n" +
                "'username' = 'root',\n" +
                "'password' = 'dge_1992@163.com',\n" +
                "'database-name' = 'test1',\n" +
                "'table-name' = 't1');");

        Table table = tableEnvironment.sqlQuery("select * from t1");
        table.execute()
                .print();
    }
}
