package com.github.elliot.flinkdemo.sink;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.clients.producer.ProducerConfig;

public class KafkaSinkTest {

    public static void main(String[] args) throws Exception {
        // create environment
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        // set parallelism
        env.setParallelism(1);
        env.enableCheckpointing(2000, CheckpointingMode.EXACTLY_ONCE);
        // readSource
        DataStreamSource<String> source = env.socketTextStream("10.211.55.4", 7777);
        // kafka sink
        KafkaSink<String> kafkaSink = KafkaSink.<String>builder()
                // 指定kafka地址和端口
                .setBootstrapServers("10.211.55.4:9092")
                // 指定kafka的Topic和序列化
                .setRecordSerializer(KafkaRecordSerializationSchema.<String>builder()
                        .setTopic("ws")
                        .setValueSerializationSchema(new SimpleStringSchema())
                        .build())
                // 设置kafka的一致性级别: 精准一次
                .setDeliveryGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
                // 精准一次必须设置事务的前缀
                .setTransactionalIdPrefix("atguigu-")
                // 精准一次必须设置事务的超时事件
                .setProperty(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, 10 * 60 * 1000 + "")
                .build();
        // sink
        source.sinkTo(kafkaSink);
        // execute
        env.execute();
    }
}
