package com.github.elliot.flinkdemo.process;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimerService;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.time.Duration;
import java.util.*;

/**
 * ProcessAllWindowFunction
 */
public class TopNDemo {

    public static void main(String[] args) throws Exception {

        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
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
        SingleOutputStreamOperator<WaterSensor> singleOutputStreamOperator =
                streamOperator.assignTimestampsAndWatermarks(waterSensorWatermarkStrategy);

        singleOutputStreamOperator.windowAll(SlidingEventTimeWindows.of(Time.seconds(10), Time.seconds(5)))
                        .process(new ProcessAllWindowFunction<WaterSensor, String, TimeWindow>() {
                            @Override
                            public void process(ProcessAllWindowFunction<WaterSensor, String, TimeWindow>.Context context, Iterable<WaterSensor> elements, Collector<String> out) throws Exception {
                                Map<Integer, Integer> map = new HashMap<>();
                                for (WaterSensor element : elements) {
                                    Integer vc = element.getVc();
                                    if(map.containsKey(vc)){
                                        map.put(vc, map.get(vc) + 1);
                                    }else{
                                        map.put(vc, 1);
                                    }
                                }
                                List<Tuple2<Integer, Integer>> list = new ArrayList<>();
                                for (Integer key : map.keySet()) {
                                    list.add(Tuple2.of(key, map.get(key)));
                                }
                                list.sort(new Comparator<Tuple2<Integer, Integer>>() {
                                    @Override
                                    public int compare(Tuple2<Integer, Integer> o1, Tuple2<Integer, Integer> o2) {
                                        return o2.f1 - o1.f1;
                                    }
                                });
                                StringBuilder sb = new StringBuilder();
                                sb.append("=================");
                                for (int i = 0; i < Math.min(2, list.size()); i++) {
                                    long end = context.window().getEnd();
                                    String date = DateFormatUtils.format(end, "yyyy-MM-dd HH:mm:ss:SSS");
                                    sb.append("时间:").append(date);
                                    sb.append("第").append(i + 1).append("的排名是:");
                                    Tuple2<Integer, Integer> tuple2 = list.get(i);
                                    sb.append("vc:").append(tuple2.f0);
                                    sb.append("count:").append(tuple2.f1);
                                    sb.append("=================");
                                }
                                out.collect(sb.toString());
                            }
                        })
                .print();

        env.execute();
    }
}
