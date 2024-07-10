package com.github.elliot.flinkdemo.filter;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * 数据分流
 */
public class FilterDemo {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> source = env.socketTextStream("10.211.55.4", 7777);

        source.filter(e -> Integer.parseInt(e) % 2 == 0).print("偶数流");

        source.filter(e -> Integer.parseInt(e) % 2 == 1).print("奇数流");

        env.execute();
    }
}
