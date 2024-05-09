package com.github.elliot.flinkdemo.demo;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * 单词统计练习
 */
public class WordCountStreamTest {

    public static void main(String[] args) throws Exception {
        // 创建环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 读取数据
        DataStreamSource<String> stringDataStreamSource = env.readTextFile("flink-demo/data/word_count.txt");
        // 处理数据
        SingleOutputStreamOperator<Tuple2<String, Integer>> singleOutputStreamOperator
                = stringDataStreamSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] words = value.split(" ");
                for (String word : words) {
                    Tuple2<String, Integer> tuple2 = Tuple2.of(word, 1);
                    out.collect(tuple2);
                }
            }
        });

        // 处理数据 - 分组
        KeyedStream<Tuple2<String, Integer>, String> tuple2StringKeyedStream =
                singleOutputStreamOperator.keyBy(new KeySelector<Tuple2<String, Integer>, String>() {
            @Override
            public String getKey(Tuple2<String, Integer> value) throws Exception {
                return value.f0;
            }
        });
        // 处理数据 - 聚合
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = tuple2StringKeyedStream.sum(1);
        // 打印
        sum.print();
        // 执行
        env.execute();
    }
}
