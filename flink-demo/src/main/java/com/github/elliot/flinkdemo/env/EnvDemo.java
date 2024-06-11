package com.github.elliot.flinkdemo.env;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Flink 环境测试类
 */
public class EnvDemo {

    public static void main(String[] args) throws Exception {
        // 获取环境
        Configuration configuration = new Configuration();
        configuration.set(RestOptions.BIND_PORT, "8082");
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(configuration);

        // 设置运行模式 - 流批一体 默认是流处理方式
        // 提交参数指定
        env.setRuntimeMode(RuntimeExecutionMode.STREAMING);

        DataStreamSource<String> stringDataStreamSource =
                env.socketTextStream("10.211.55.4", 7777);
                //env.readTextFile("/Users/ganendong/Documents/workspace/springboot-demo/flink-demo/data/word_count.txt");

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
                .print();

        // 触发执行
        env.execute();
    }
}
