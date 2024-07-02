package com.github.elliot.flinkdemo.transform;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FilterDemo {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<WaterSensor> source = env.fromElements(
                new WaterSensor("s1", 1L, 1),
                new WaterSensor("s2", 2l, 2),
                new WaterSensor("s3", 3l, 3),
                new WaterSensor("s1", 3l, 3));


        source.filter(new FilterFunction<WaterSensor>() {
            @Override
            public boolean filter(WaterSensor value) throws Exception {
                return "s1".equals(value.getId());
            }
        }).print();

        env.execute();
    }
}
