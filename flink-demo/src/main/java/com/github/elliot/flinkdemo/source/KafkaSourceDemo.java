package com.github.elliot.flinkdemo.source;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class KafkaSourceDemo {

    public static void main(String[] args) throws Exception {
        // 获取环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 创建source
        KafkaSource<String> source = KafkaSource.<String>builder()
                .setBootstrapServers("10.211.55.4:9092")
                .setGroupId("elliot")
                .setTopics("test")
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .setStartingOffsets(OffsetsInitializer.latest())
                .build();
        env.fromSource(source, WatermarkStrategy.noWatermarks(), "kafka_source")
                .print();
        // 执行
        env.execute();
    }
}
