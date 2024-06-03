package com.github.elliot.flinkdemo.demo;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * 单词统计练习 - 无界数据
 */
public class WordCountStreamUnboundedTest {

    public static void main(String[] args) throws Exception {
        // 创建环境
        // StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());

        env.setParallelism(1);
        // 全局禁用算子链合并
        // env.disableOperatorChaining();
        // 读取数据
        // DataStreamSource<String> stringDataStreamSource = env.readTextFile("flink-demo/data/word_count.txt");
        // 读取数据
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
                //.setParallelism(2)
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy((KeySelector<Tuple2<String, Integer>, Object>) value -> value.f0)
                .sum(1)
                //.disableChaining()
                .print();

        // 执行
        env.execute();
    }
}
