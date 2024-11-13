package com.github.elliot.flinkdemo.watermark;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.Partitioner;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.time.Duration;

/**
 * 水位线设置空闲等待
 * .withIdleness(Duration.ofSeconds(5)); //表示过了5秒小的水位线的数据就作废
 */
public class WaterMarkIdlenessDome {

    public static void main(String[] args) throws Exception {
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        env.setParallelism(2);
        SingleOutputStreamOperator<Integer> streamOperator = env.socketTextStream("10.211.55.4", 7777)
                .partitionCustom(new Partitioner<String>() {
                    @Override
                    public int partition(String key, int numPartitions) {
                        return Integer.parseInt(key) % 2;
                    }
                }, new KeySelector<String, String>() {
                    @Override
                    public String getKey(String value) throws Exception {
                        return value;
                    }
                }).map(Integer::valueOf);

        // 自定义水位线
        WatermarkStrategy<Integer> waterSensorWatermarkStrategy = WatermarkStrategy.<Integer>forMonotonousTimestamps()
                .withTimestampAssigner(new SerializableTimestampAssigner<Integer>() {
                    @Override
                    public long extractTimestamp(Integer element, long recordTimestamp) {
                        System.out.println("element:" + element + " recordTimestamp:" + recordTimestamp);
                        return element * 1000L;
                    }
                })
                .withIdleness(Duration.ofSeconds(5));
        SingleOutputStreamOperator<String> process = streamOperator.assignTimestampsAndWatermarks(waterSensorWatermarkStrategy)
                .keyBy(k -> k % 2)
                .window(TumblingEventTimeWindows.of(Time.seconds(10)))
                .process(new ProcessWindowFunction<Integer, String, Integer, TimeWindow>() {
                    @Override
                    public void process(Integer integer, ProcessWindowFunction<Integer, String, Integer, TimeWindow>.Context context, Iterable<Integer> elements, Collector<String> out) throws Exception {
                        long start = context.window().getStart();
                        long end = context.window().getEnd();
                        String startStr = DateFormatUtils.format(start, "yyyy-MM-dd HH:mm:ss.SSS");
                        String endStr = DateFormatUtils.format(end, "yyyy-MM-dd HH:mm:ss.SSS");
                        long count = elements.spliterator().estimateSize();
                        out.collect(" 时间段[" + startStr + "-" + endStr + "]" + "的个数是:" + count + "数据集为:" + elements.toString());

                    }
                });
        process.print();
        env.execute();
    }
}
