package com.github.elliot.flinkdemo.source;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 * 从集合读取数据
 */
public class CollectionDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // DataStreamSource<Integer> source = env.fromCollection(Arrays.asList(111, 222, 333, 444));
        DataStreamSource<Integer> source = env.fromElements(111, 222, 333, 444);
        source.print();
        env.execute();
    }
}
