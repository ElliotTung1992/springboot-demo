package com.github.elliot.flinkdemo.combination;

import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;

/**
 * 合并流
 */
public class ConnectDemo {

    public static void main(String[] args) throws Exception {
        // 创建环境
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        // 设置并行度
        environment.setParallelism(1);
        // 创建数据
        DataStreamSource<Integer> source1 = environment.fromElements(1, 2, 3);
        DataStreamSource<Integer> source3 = environment.fromElements(11, 22, 33);
        DataStreamSource<String> source2 = environment.fromElements("a", "b", "c");
        // 流连接
        ConnectedStreams<Integer, String> connectedStreams = source1.connect(source2);
        // 流处理
        SingleOutputStreamOperator<String> singleOutputStreamOperator = connectedStreams.map(new CoMapFunction<Integer, String, String>() {
            @Override
            public String map1(Integer value) throws Exception {
                return value.toString();
            }

            @Override
            public String map2(String value) throws Exception {
                return value;
            }
        });
        // 打印
        singleOutputStreamOperator.print();
        // 执行
        environment.execute();
    }
}
