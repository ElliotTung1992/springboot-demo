package com.github.elliot.flinkdemo.process;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.TimerService;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.time.Duration;

/**
 * KeyedProcessFunction timer
 */
public class KeyProcessTimerDemo {

    public static void main(String[] args) throws Exception {

        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();

        // 设置并行度为2
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
                        return element.getSt() * 1000L;
                    }
                });
        SingleOutputStreamOperator<String> process = streamOperator.assignTimestampsAndWatermarks(waterSensorWatermarkStrategy)
                .keyBy(WaterSensor::getId).process(new KeyedProcessFunction<String, WaterSensor, String>() {

                    @Override
                    public void processElement(WaterSensor value, KeyedProcessFunction<String, WaterSensor, String>.Context ctx, Collector<String> out) throws Exception {
                        /*// 获取当前key
                        String currentKey = ctx.getCurrentKey();
                        // 获取水位线时间
                        Long timestamp = ctx.timestamp();
                        // 获取定时器
                        TimerService timerService = ctx.timerService();
                        // 注册时间时间定时器
                        timerService.registerEventTimeTimer(5000L);
                        System.out.println("currentKey:" + currentKey + " currentTimeStamp:" + timestamp);*/

                        TimerService timerService = ctx.timerService();
                        long currentProcessingTime = timerService.currentProcessingTime();
                        timerService.registerProcessingTimeTimer(currentProcessingTime + 5000L);
                        System.out.println("currentProcessingTime is: " + currentProcessingTime + " register a timer after 5 seconds");
                    }

                    @Override
                    public void onTimer(long timestamp, KeyedProcessFunction<String, WaterSensor, String>.OnTimerContext ctx, Collector<String> out) throws Exception {
                        super.onTimer(timestamp, ctx, out);
                        // 获取当前key
                        String currentKey = ctx.getCurrentKey();
                        System.out.println("currentKey：" + currentKey + " 定时器执行了");
                    }
                });
        process.print();
        env.execute();
    }
}
