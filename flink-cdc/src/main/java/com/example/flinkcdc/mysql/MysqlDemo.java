package com.example.flinkcdc.mysql;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * MysqlDemo
 */
public class MysqlDemo {

    public static void main(String[] args) throws Exception {

        // Get Flink Env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        // start checkpoint
        /*env.enableCheckpointing(5000L);
        CheckpointConfig checkpointConfig = env.getCheckpointConfig();
        checkpointConfig.setCheckpointTimeout(10000L);
        checkpointConfig.setCheckpointStorage("hdfs://10.211.55.4:8020/flinkCDC/ck");
        checkpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        checkpointConfig.setMaxConcurrentCheckpoints(1);*/

        // build MysqlSource
        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname("10.211.55.2")
                .port(3306)
                .username("root")
                .password("dge_1992@163.com")
                .databaseList("test1")
                .tableList("test1.t1")
                .startupOptions(StartupOptions.latest())
                .deserializer(new JsonDebeziumDeserializationSchema())
                .build();

        DataStreamSource<String> source
                = env.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "mysql-source");

        source.print();

        env.execute();
    }
}
