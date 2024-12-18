package com.github.elliot.flinkdemo.sql;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * 流于表 表与流之间相互转换
 */
public class TestStreamDemo {

    public static void main(String[] args) throws Exception {
        // create env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // source
        DataStreamSource<WaterSensor> source = env.fromElements(
                new WaterSensor("s1", 1L, 1),
                new WaterSensor("s1", 1L, 2),
                new WaterSensor("s2", 2L, 2),
                new WaterSensor("s2", 2L, 3),
                new WaterSensor("s3", 3L, 3));
        // create table env
        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(env);

        // 表转流
        Table table = tableEnvironment.fromDataStream(source);
        tableEnvironment.createTemporaryView("sensor", table);

        Table filterTable = tableEnvironment.sqlQuery("select * from sensor where vc > 2");
        Table sumTable = tableEnvironment.sqlQuery("select id, sum(vc) as sumVc from sensor group by id");

        // 流转表
        tableEnvironment.toDataStream(filterTable, WaterSensor.class).print("filter");
        tableEnvironment.toChangelogStream(sumTable).print("sum");

        env.execute();
    }
}
