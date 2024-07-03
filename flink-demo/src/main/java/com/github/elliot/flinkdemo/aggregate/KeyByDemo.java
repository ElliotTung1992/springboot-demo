package com.github.elliot.flinkdemo.aggregate;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class KeyByDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStreamSource<WaterSensor> source = env.fromElements(
                new WaterSensor("s1", 1L, 1),
                new WaterSensor("s2", 2l, 2),
                new WaterSensor("s3", 3l, 3),
                new WaterSensor("s1", 4l, 4));

        KeyedStream<WaterSensor, String> keyedStream = source.keyBy(new KeySelector<WaterSensor, String>() {
            @Override
            public String getKey(WaterSensor sensor) throws Exception {
                return sensor.getId();
            }
        });

        // SingleOutputStreamOperator<WaterSensor> streamOperator = keyedStream.sum("vc");

        // SingleOutputStreamOperator<WaterSensor> streamOperator = keyedStream.min("vc");

        // SingleOutputStreamOperator<WaterSensor> streamOperator = keyedStream.max("vc");

        // SingleOutputStreamOperator<WaterSensor> streamOperator = keyedStream.maxBy("vc");

        SingleOutputStreamOperator<WaterSensor> streamOperator = keyedStream.reduce(new ReduceFunction<WaterSensor>() {
            @Override
            public WaterSensor reduce(WaterSensor value1, WaterSensor value2) throws Exception {
                return new WaterSensor(value2.getId(), value2.getSt(), value1.getVc() + value2.getVc());
            }
        });

        streamOperator.print();

        env.execute();
    }
}
