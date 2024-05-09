package com.github.elliot.flinkdemo.demo;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * 单词统计练习 - DataSet写法
 */
public class WordCountTest {

    public static void main(String[] args) throws Exception {
        // 创建环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        // 读取数据
        DataSource<String> dataSource = env.readTextFile("/Users/ganendong/Documents/workspace/springboot-demo/flink-demo/data/word_count.txt");
        // 转换数据 - 分割 转换
        FlatMapOperator<String, Tuple2<String, Integer>> flatMap = dataSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {

            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] words = value.split(" ");
                for (String word : words) {
                    Tuple2<String, Integer> tuple2 = Tuple2.of(word, 1);
                    out.collect(tuple2);
                }
            }
        });
        // 分组
        UnsortedGrouping<Tuple2<String, Integer>> tuple2UnsortedGrouping = flatMap.groupBy(0);
        // 聚合
        AggregateOperator<Tuple2<String, Integer>> sum =
                tuple2UnsortedGrouping.sum(1);
        // 输出打印
        sum.print();
    }
}
