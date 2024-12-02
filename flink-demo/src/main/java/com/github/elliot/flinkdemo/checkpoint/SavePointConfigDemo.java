package com.github.elliot.flinkdemo.checkpoint;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.time.Duration;

/**
 * 检查点配置
 */
public class SavePointConfigDemo {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        env.setParallelism(1);

        System.setProperty("HADOOP_USER_NAME", "hadoop");

        // 检查点配置
        // 开启检查点配置 - 设置多久触发一次检查点和设置检查点算法
        env.enableCheckpointing(5000L, CheckpointingMode.EXACTLY_ONCE);
        CheckpointConfig checkpointConfig = env.getCheckpointConfig();
        // 设置checkpoint的数据存储位置
        checkpointConfig.setCheckpointStorage("hdfs://10.211.55.4:8020/checkpoint");
        // 设置checkpoint的超时时间
        checkpointConfig.setCheckpointTimeout(60 * 1000);
        // 设置checkout的最大并发数
        checkpointConfig.setMaxConcurrentCheckpoints(1);
        // 设置连续两次checkpoint之间的最小间隔时间
        checkpointConfig.setMinPauseBetweenCheckpoints(1 * 1000);
        // 当任务取消时设置checkpoint存储数据清理策略
        checkpointConfig.setExternalizedCheckpointCleanup(CheckpointConfig.ExternalizedCheckpointCleanup.DELETE_ON_CANCELLATION);
        // 设置checkpoint连续失败的次数
        checkpointConfig.setTolerableCheckpointFailureNumber(10);

        // 开启非堆成检查点 条件: CheckpointingMode必须是精准一次, MaxConcurrentCheckpoints必须是1
        checkpointConfig.enableUnalignedCheckpoints();
        checkpointConfig.setAlignedCheckpointTimeout(Duration.ofSeconds(10));

        DataStreamSource<String> stringDataStreamSource =
                env.socketTextStream("10.211.55.4", 7777);

        // 处理数据
        stringDataStreamSource.flatMap((FlatMapFunction<String, Tuple2<String, Integer>>) (value, out) -> {
                    String[] words = value.split(" ");
                    for (String word : words) {
                        Tuple2<String, Integer> tuple2 = Tuple2.of(word, 1);
                        out.collect(tuple2);
                    }
                })
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy((KeySelector<Tuple2<String, Integer>, Object>) value -> value.f0)
                .sum(1)
                .uid("SavePointConfigDemo-sum").name("SavePointConfigDemo-sum-name")
                .print();

        // 执行
        env.execute();
    }
}
