package com.github.elliot.flinkdemo.process;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.TimerService;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.time.Duration;
import java.util.*;

/**
 * KeyedProcessFunction timer
 */
public class KeyProcessTopNDemo {

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

        SingleOutputStreamOperator<Tuple3<Integer, Integer, Long>> outputStreamOperator = singleOutputStreamOperator.keyBy(WaterSensor::getVc)
                .window(SlidingEventTimeWindows.of(Time.seconds(10), Time.seconds(5)))
                .aggregate(new AggregateFunction<WaterSensor, Integer, Integer>() {
                    @Override
                    public Integer createAccumulator() {
                        return 0;
                    }

                    @Override
                    public Integer add(WaterSensor value, Integer accumulator) {
                        return accumulator + 1;
                    }

                    @Override
                    public Integer getResult(Integer accumulator) {
                        return accumulator;
                    }

                    @Override
                    public Integer merge(Integer a, Integer b) {
                        return null;
                    }
                }, new ProcessWindowFunction<Integer, Tuple3<Integer, Integer, Long>, Integer, TimeWindow>() {
                    @Override
                    public void process(Integer key, ProcessWindowFunction<Integer, Tuple3<Integer, Integer, Long>, Integer, TimeWindow>.Context context, Iterable<Integer> elements, Collector<Tuple3<Integer, Integer, Long>> out) throws Exception {
                        Integer count = elements.iterator().next();
                        long end = context.window().getEnd();
                        out.collect(Tuple3.of(key, count, end));
                    }
                });

        SingleOutputStreamOperator<String> process = outputStreamOperator.keyBy(e -> e.f2)
                .process(new KeyedProcessFunction<Long, Tuple3<Integer, Integer, Long>, String>() {

                    Map<Long, List<Tuple3<Integer, Integer, Long>>> map = new HashMap<>();

                    @Override
                    public void processElement(Tuple3<Integer, Integer, Long> value, KeyedProcessFunction<Long, Tuple3<Integer, Integer, Long>, String>.Context ctx, Collector<String> out) throws Exception {
                        Long f2 = value.f2;
                        if (map.containsKey(f2)) {
                            List<Tuple3<Integer, Integer, Long>> list = map.get(f2);
                            list.add(value);
                        }else{
                            List<Tuple3<Integer, Integer, Long>> list = new ArrayList<>();
                            list.add(value);
                            map.put(f2, list);
                        }

                        TimerService timerService = ctx.timerService();
                        timerService.registerEventTimeTimer(f2 + 1);
                    }

                    @Override
                    public void onTimer(long timestamp, KeyedProcessFunction<Long, Tuple3<Integer, Integer, Long>, String>.OnTimerContext ctx, Collector<String> out) throws Exception {
                        super.onTimer(timestamp, ctx, out);
                        Long currentKey = ctx.getCurrentKey();
                        List<Tuple3<Integer, Integer, Long>> list = map.get(currentKey);

                        list.sort(new Comparator<Tuple3<Integer, Integer, Long>>() {
                            @Override
                            public int compare(Tuple3<Integer, Integer, Long> o1, Tuple3<Integer, Integer, Long> o2) {
                                return o2.f1 - o1.f1;
                            }
                        });
                        StringBuilder sb = new StringBuilder();
                        sb.append("=================");
                        for (int i = 0; i < Math.min(2, list.size()); i++) {
                            String date = DateFormatUtils.format(currentKey, "yyyy-MM-dd HH:mm:ss:SSS");
                            sb.append("时间:").append(date);
                            sb.append("第").append(i + 1).append("的排名是:");
                            Tuple3<Integer, Integer, Long> tuple3 = list.get(i);
                            sb.append("vc:").append(tuple3.f0);
                            sb.append("count:").append(tuple3.f1);
                            sb.append("=================");
                        }
                        out.collect(sb.toString());
                    }
                });

        process.print();

        env.execute();
    }
}
