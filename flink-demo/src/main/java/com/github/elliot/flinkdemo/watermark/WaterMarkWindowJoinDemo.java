package com.github.elliot.flinkdemo.watermark;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * 双流连接开窗口
 */
public class WaterMarkWindowJoinDemo {

    public static void main(String[] args) throws Exception {
        // 创建环境
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        env.setParallelism(1);
        // 流1
        SingleOutputStreamOperator<Tuple2<String, Integer>> streamOperator1 =
                env.fromElements(new Tuple2<>("a", 1), new Tuple2<>("a", 2), new Tuple2<>("a", 11),
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
        // join
        DataStream<String> dataStream = streamOperator1.join(streamOperator2)
                .where(t -> t.f0)
                .equalTo(t -> t.f0)
                .window(TumblingEventTimeWindows.of(Time.seconds(5)))
                .apply(new JoinFunction<Tuple2<String, Integer>, Tuple2<String, Integer>, String>() {
                    @Override
                    public String join(Tuple2<String, Integer> first, Tuple2<String, Integer> second) throws Exception {
                        return first.toString() + "<===>" + second.toString();
                    }
                });

        dataStream.print();

        env.execute();
    }
}
