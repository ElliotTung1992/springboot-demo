package com.github.elliot.flinkdemo.transform;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class MapDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<WaterSensor> dataStreamSource = env.fromElements(new WaterSensor("s1", 1L, 1),
                new WaterSensor("s2", 2l, 2),
                new WaterSensor("s3", 3l, 3));

        /*SingleOutputStreamOperator<String> map = dataStreamSource.map(new MapFunction<WaterSensor, String>() {

            @Override
            public String map(WaterSensor value) throws Exception {
                return value.getId();
            }
        });*/

        // SingleOutputStreamOperator<String> operator = dataStreamSource.map(WaterSensor::getId);

        SingleOutputStreamOperator<String> operator = dataStreamSource.map(new MyMapFunction());

        operator.print();

        env.execute();
    }

    public static class MyMapFunction implements MapFunction<WaterSensor, String> {

        @Override
        public String map(WaterSensor value) throws Exception {
            return value.getId();
        }
    }
}
