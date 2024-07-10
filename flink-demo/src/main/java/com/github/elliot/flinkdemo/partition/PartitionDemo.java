package com.github.elliot.flinkdemo.partition;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * 分区器
 */
public class PartitionDemo {

    public static void main(String[] args) throws Exception {
        // StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());

        env.setParallelism(2);

        DataStreamSource<String> source = env.socketTextStream("10.211.55.4", 7777);

        // 随机
        // source.shuffle().print();

        // 轮训
        // source.rebalance().print();

        // 局部组队轮训
        // source.rescale().print();

        // 广播
        // source.broadcast().print();

        // 永远是第一个
        source.global().print();

        env.execute();
    }
}
