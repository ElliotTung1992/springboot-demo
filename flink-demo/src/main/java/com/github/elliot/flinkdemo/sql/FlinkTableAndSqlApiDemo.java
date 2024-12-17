package com.github.elliot.flinkdemo.sql;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import static org.apache.flink.table.api.Expressions.$;

public class FlinkTableAndSqlApiDemo {

    public static void main(String[] args) {
        // create env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // create table env 方式一
        /*EnvironmentSettings environmentSettings = EnvironmentSettings.newInstance()
                .inStreamingMode()
                .build();
        TableEnvironment tableEnvironment = TableEnvironment.create(environmentSettings);*/
        // create table env 方式二
        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(env);

        // create table
        tableEnvironment.executeSql("CREATE TABLE source (\n" +
                "    id INT,\n" +
                "    ts BIGINT,\n" +
                "    vc INT\n" +
                ") WITH (\n" +
                "    'connector' = 'datagen',\n" +
                "    'rows-per-second'='1',\n" +
                "    'fields.id.kind'='random',\n" +
                "    'fields.id.min'='1',\n" +
                "    'fields.id.max'='10',\n" +
                "    'fields.ts.kind'='sequence',\n" +
                "    'fields.ts.start'='1',\n" +
                "    'fields.ts.end'='1000000',\n" +
                "    'fields.vc.kind'='random',\n" +
                "    'fields.vc.min'='1',\n" +
                "    'fields.vc.max'='100'\n" +
                ");");

        tableEnvironment.executeSql("CREATE TABLE sink (\n" +
                "    id INT,\n" +
                "    sumVc BIGINT\n" +
                ") WITH (\n" +
                "'connector' = 'print'\n" +
                ");");

        // execute sql
        /*Table table = tableEnvironment.sqlQuery("select id, sum(vc) as sumVc from source where id > 5 group by id;");
        tableEnvironment.createTemporaryView("tmp", table);
        tableEnvironment.executeSql("insert into sink select * from tmp;");*/

        // sql api
        Table source = tableEnvironment.from("source");
        Table result = source.where($("id").isGreater(5))
                .groupBy($("id"))
                .aggregate($("vc").sum().as("sumVc"))
                .select($("id"), $("sumVc"));
        result.executeInsert("sink");
    }
}
