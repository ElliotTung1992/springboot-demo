package com.github.elliot.flinkdemo.watermark;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SideOutputDataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.ProcessJoinFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.time.Duration;

/**
 * 无序流合并
 */
public class WaterMarkIntervalJoinUnorderedDemo {

    public static void main(String[] args) throws Exception {
        // 创建环境
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        env.setParallelism(1);
        // 流1
        WatermarkStrategy<WaterSensor> waterSensorWatermarkStrategy = WatermarkStrategy.<WaterSensor>forBoundedOutOfOrderness(Duration.ofSeconds(2))
                .withTimestampAssigner(new SerializableTimestampAssigner<WaterSensor>() {
                    @Override
                    public long extractTimestamp(WaterSensor element, long recordTimestamp) {
                        return element.getSt() * 1000;
                    }
                });
        SingleOutputStreamOperator<WaterSensor> singleOutputStreamOperator1 = env.socketTextStream("10.211.55.4", 7777)
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
                }).assignTimestampsAndWatermarks(waterSensorWatermarkStrategy);

        //  流2
        SingleOutputStreamOperator<WaterSensor> singleOutputStreamOperator2 = env.socketTextStream("10.211.55.4", 8888)
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
                }).assignTimestampsAndWatermarks(waterSensorWatermarkStrategy);

        // keyBy
        KeyedStream<WaterSensor, String> waterSensorStringKeyedStream1 = singleOutputStreamOperator1.keyBy(k -> k.getId());
        KeyedStream<WaterSensor, String> waterSensorStringKeyedStream2 = singleOutputStreamOperator2.keyBy(k -> k.getId());

        // interval join
        OutputTag<WaterSensor> outputTagLeft = new OutputTag<>("left", TypeInformation.of(WaterSensor.class));
        OutputTag<WaterSensor> outputTagRight = new OutputTag<>("right", TypeInformation.of(WaterSensor.class));
        SingleOutputStreamOperator<String> process = waterSensorStringKeyedStream1.intervalJoin(waterSensorStringKeyedStream2)
                .between(Time.seconds(-2), Time.seconds(2))
                .sideOutputLeftLateData(outputTagLeft)
                .sideOutputRightLateData(outputTagRight)
                .process(new ProcessJoinFunction<WaterSensor, WaterSensor, String>() {
                    @Override
                    public void processElement(WaterSensor left, WaterSensor right,
                                               ProcessJoinFunction<WaterSensor, WaterSensor, String>.Context ctx,
                                               Collector<String> out) throws Exception {
                        out.collect(left.toString() + "<====>" + right.toString());
                    }
                });
        process.print();

        // sideOutput
        SideOutputDataStream<WaterSensor> sideOutputLeft = process.getSideOutput(outputTagLeft);
        sideOutputLeft.printToErr();
        SideOutputDataStream<WaterSensor> sideOutputRight = process.getSideOutput(outputTagRight);
        sideOutputRight.printToErr();

        env.execute();
    }
}
