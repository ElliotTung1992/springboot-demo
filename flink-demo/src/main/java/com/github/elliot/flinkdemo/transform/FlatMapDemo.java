package com.github.elliot.flinkdemo.transform;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class FlatMapDemo {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<WaterSensor> source = env.fromElements(new WaterSensor("s1", 1L, 1),
                new WaterSensor("s2", 2L, 2),
                new WaterSensor("s3", 3L, 3),
                new WaterSensor("s1", 11L, 11));

        source.flatMap(new FlatMapFunction<WaterSensor, String>() {
            @Override
            public void flatMap(WaterSensor sensor, Collector<String> out) throws Exception {
                if("s1".equals(sensor.getId())){
                    out.collect(sensor.getSt().toString());
                }else if("s2".equals(sensor.getId())){
                    out.collect(sensor.getSt().toString());
                    out.collect(sensor.getVc().toString());
                }
            }
        }).print();
        
        env.execute();
    }
}
