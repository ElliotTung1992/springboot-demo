package com.github.elliot.flinkdemo.watermark;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.ProcessJoinFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * 连接流 - 进行间隔连接
 */
public class WaterMarkIntervalJoinDemo {

    public static void main(String[] args) throws Exception {
        // 创建环境
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        env.setParallelism(1);
        // 流1
        SingleOutputStreamOperator<Tuple2<String, Integer>> streamOperator1 =
                env.fromElements(new Tuple2<>("a", 1), new Tuple2<>("a", 2), new Tuple2<>("a", 6),
                                new Tuple2<>("b", 2), new Tuple2<>("b", 14), new Tuple2<>("c", 1))
                        .assignTimestampsAndWatermarks(WatermarkStrategy
                                .<Tuple2<String, Integer>>forMonotonousTimestamps()
                                .withTimestampAssigner((value, ts) -> value.f1 * 1000L));
        //  流2
        SingleOutputStreamOperator<Tuple2<String, Integer>> streamOperator2 =
                env.fromElements(new Tuple2<>("a", 3), new Tuple2<>("b", 2))
                        .assignTimestampsAndWatermarks(WatermarkStrategy
                                .<Tuple2<String, Integer>>forMonotonousTimestamps()
                                .withTimestampAssigner((value, ts) -> value.f1 * 1000L));
        // keyBy
        KeyedStream<Tuple2<String, Integer>, String> keyedStream1 = streamOperator1.keyBy(k -> k.f0);
        KeyedStream<Tuple2<String, Integer>, String> keyedStream2 = streamOperator2.keyBy(k -> k.f0);
        // interval join
        SingleOutputStreamOperator<String> process = keyedStream1.intervalJoin(keyedStream2)
                .between(Time.seconds(-2), Time.seconds(2))
                .process(new ProcessJoinFunction<Tuple2<String, Integer>, Tuple2<String, Integer>, String>() {
                    @Override
                    public void processElement(Tuple2<String, Integer> left, Tuple2<String, Integer> right,
                                               ProcessJoinFunction<Tuple2<String, Integer>, Tuple2<String, Integer>,
                                                       String>.Context ctx, Collector<String> out) throws Exception {
                        out.collect(left.toString() + "<===>" + right.toString());
                    }
                });
        process.print();
        env.execute();
    }
}
