package com.github.elliot.flinkdemo.watermark;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
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
 * 水位线 - 允许迟到 - 延迟关窗
 */
public class WaterMarkAllowLatenessDemo {

    public static void main(String[] args) throws Exception {

        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();

        // 设置水位线生成间隔
        // env.getConfig().setAutoWatermarkInterval(200L);

        // 设置并行度为1
        env.setParallelism(1);

        SingleOutputStreamOperator<WaterSensor> streamOperator = env.socketTextStream("10.211.55.4", 7777)
                .flatMap(new FlatMapFunction<String, WaterSensor>() {
                    @Override
                    public void flatMap(String value, Collector<WaterSensor> out) throws Exception {
                        String[] split = value.split(",");
                        WaterSensor waterSensor = new WaterSensor();
                        waterSensor.setId(split[0]);
                        waterSensor.setSt(Long.valueOf(split[1]));
                        waterSensor.setVc(Integer.valueOf(split[2]));
                        out.collect(waterSensor);
                    }
                });

        // 自定义水位线
        WatermarkStrategy<WaterSensor> waterSensorWatermarkStrategy = WatermarkStrategy.<WaterSensor>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                .withTimestampAssigner(new SerializableTimestampAssigner<WaterSensor>() {
                    @Override
                    public long extractTimestamp(WaterSensor element, long recordTimestamp) {
                        System.out.println("element:" + element + " recordTimestamp:" + recordTimestamp);
                        return element.getSt() * 1000L;
                    }
                });
        SingleOutputStreamOperator<String> process = streamOperator.assignTimestampsAndWatermarks(waterSensorWatermarkStrategy)
                .keyBy(WaterSensor::getId)
                .window(TumblingEventTimeWindows.of(Time.seconds(10)))
                // 允许迟到 - 延迟关窗
                .allowedLateness(Time.seconds(2))
                .process(new ProcessWindowFunction<WaterSensor, String, String, TimeWindow>() {
                    @Override
                    public void process(String s, ProcessWindowFunction<WaterSensor, String, String,
                            TimeWindow>.Context context, Iterable<WaterSensor> elements, Collector<String> out) throws Exception {
                        long start = context.window().getStart();
                        long end = context.window().getEnd();
                        String startStr = DateFormatUtils.format(start, "yyyy-MM-dd HH:mm:ss.SSS");
                        String endStr = DateFormatUtils.format(end, "yyyy-MM-dd HH:mm:ss.SSS");
                        long count = elements.spliterator().estimateSize();
                        out.collect("key:" + s + " 时间段[" + startStr + "-" + endStr + "]" + "的个数是:" + count + "数据集为:" + elements.toString());
                    }
                });
        process.print();
        env.execute();
    }
}
