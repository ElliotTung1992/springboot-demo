package com.github.elliot.flinkdemo.partition;

import com.github.elliot.flinkdemo.function.MyPartitioner;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * 自定义分区器
 */
public class PartitionCustomDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(2);

        DataStreamSource<String> source = env.socketTextStream("10.211.55.4", 7777);

        source.partitionCustom(new MyPartitioner(), (KeySelector<String, Integer>) Integer::valueOf).print();

        env.execute();
    }
}
