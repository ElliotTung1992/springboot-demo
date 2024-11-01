package com.github.elliot.flinkdemo.window;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import org.apache.flink.streaming.api.windowing.assigners.ProcessingTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

public class WindowApiDemo {

    public static void main(String[] args) throws Exception {
        // create env
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        // source
        SingleOutputStreamOperator<WaterSensor> streamOperator = env.socketTextStream("10.211.55.4", 7777)
                .flatMap((FlatMapFunction<String, WaterSensor>) (value, out) -> {
                    String[] split = value.split(",");
                    WaterSensor waterSensor = new WaterSensor();
                    waterSensor.setId(split[0]);
                    waterSensor.setSt(Long.valueOf(split[1]));
                    waterSensor.setVc(Integer.valueOf(split[2]));
                    out.collect(waterSensor);
                });
        // keyBy
        KeyedStream<WaterSensor, String> waterSensorStringKeyedStream = streamOperator.keyBy(e -> e.getId());
        // window
        // 1 指定窗口的类型
        // 1.1 基于时间的窗口
        // 1.1.1 基于时间的窗口 - 滚动时间窗口
        // waterSensorStringKeyedStream.window(TumblingProcessingTimeWindows.of(Time.seconds(5)));
        // 1.1.2 基于时间的窗口 - 滑动时间窗口
        // waterSensorStringKeyedStream.window(SlidingProcessingTimeWindows.of(Time.seconds(5), Time.seconds(2)));
        // 1.1.3 基于时间的窗口 - 会话窗口
        // waterSensorStringKeyedStream.window(ProcessingTimeSessionWindows.withGap(Time.seconds(5)));
        // 1.2 基于个数的窗口
        // 1.2.1 基于个数的窗口 - 滚动窗口
        // waterSensorStringKeyedStream.countWindow(10);
        // 1.2.2 基于个数的窗口 - 滑动窗口
        // waterSensorStringKeyedStream.countWindow(10, 2);
        // 1.2.3 基于个数的窗口 - 全局窗口
        // waterSensorStringKeyedStream.window(GlobalWindows.create());
        // 2 指定窗口的函数

        // execute
        env.execute();
    }
}
