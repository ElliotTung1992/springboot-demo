package com.github.elliot.flinkdemo.checkpoint;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.time.Duration;

/**
 * 端到端精准一次案例
 */
public class KafkaExactlyOnceDemo {

    public static void main(String[] args) throws Exception {
        // 获取环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        env.enableCheckpointing(5000L, CheckpointingMode.EXACTLY_ONCE);// 开启保存点并设置为精准一次
        CheckpointConfig checkpointConfig = env.getCheckpointConfig();
        checkpointConfig.setCheckpointStorage("hdfs://10.211.55.4:8020/checkpoint"); // 设置checkpoint数据存放地址
        checkpointConfig.setExternalizedCheckpointCleanup(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION); // 设置取消任务时checkpoint清理策略

        // source - kafka
        KafkaSource<String> kafkaSource = KafkaSource.<String>builder()
                .setBootstrapServers("10.211.55.4:9092")
                .setGroupId("elliot")
                .setTopics("test")
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .setStartingOffsets(OffsetsInitializer.latest())
                .build();
        DataStreamSource<String> source =
                env.fromSource(kafkaSource, WatermarkStrategy.forBoundedOutOfOrderness(Duration.ofSeconds(3)), "kafka_source");

        // sink - kafka
        KafkaSink<String> kafkaSink = KafkaSink.<String>builder()
                .setBootstrapServers("10.211.55.4:9092")
                .setRecordSerializer(KafkaRecordSerializationSchema.<String>builder()
                        .setTopic("ws")
                        .setValueSerializationSchema(new SimpleStringSchema())
                        .build())
                .setDeliveryGuarantee(DeliveryGuarantee.EXACTLY_ONCE)// 精准一次 2PC事务提交
                .setTransactionalIdPrefix("elliot-") // 设置事务前缀
                .setProperty(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, 10 * 60 * 1000 + "") // 设置事务超时时间必须大于checkpoint间隔
                .build();
        source.sinkTo(kafkaSink);

        env.execute();
    }
}
