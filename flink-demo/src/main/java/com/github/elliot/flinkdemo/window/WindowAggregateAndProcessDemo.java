package com.github.elliot.flinkdemo.window;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * 增量窗口和全量窗口结合使用
 */
public class WindowAggregateAndProcessDemo {

    public static void main(String[] args) throws Exception {
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
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
        KeyedStream<WaterSensor, String> waterSensorStringKeyedStream = streamOperator.keyBy(WaterSensor::getId);
        WindowedStream<WaterSensor, String, TimeWindow> window =
                waterSensorStringKeyedStream.window(TumblingProcessingTimeWindows.of(Time.seconds(20)));
        SingleOutputStreamOperator<String> aggregate =
                window.aggregate(new MyAggregateFunction(), new MyProcessWindowFunction());
        aggregate.print();
        env.execute();
    }

    public static class MyAggregateFunction implements AggregateFunction<WaterSensor, Integer, String> {

        @Override
        public Integer createAccumulator() {
            System.out.println("createAccumulator");
            return 0;
        }

        @Override
        public Integer add(WaterSensor value, Integer accumulator) {
            System.out.println("add value:" + value + "accumulator" + accumulator);
            return value.getVc() + accumulator;
        }

        @Override
        public String getResult(Integer accumulator) {
            return "合计:" + accumulator;
        }

        @Override
        public Integer merge(Integer a, Integer b) {
            return null;
        }
    }

    public static class MyProcessWindowFunction extends ProcessWindowFunction<String, String, String, TimeWindow> {

        @Override
        public void process(String s, ProcessWindowFunction<String, String, String, TimeWindow>.Context context, Iterable<String> elements, Collector<String> out) throws Exception {
            long start = context.window().getStart();
            long end = context.window().getEnd();
            String startStr = DateFormatUtils.format(start, "yyyy-MM-dd HH:mm:ss.SSS");
            String endStr = DateFormatUtils.format(end, "yyyy-MM-dd HH:mm:ss.SSS");
            long count = elements.spliterator().estimateSize();
            out.collect("key:" + s + " 时间段[" + startStr + "-" + endStr + "]" + "的个数是:" + count + "数据集为:" + elements);
        }
    }
}
