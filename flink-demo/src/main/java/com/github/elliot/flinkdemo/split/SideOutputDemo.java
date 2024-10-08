package com.github.elliot.flinkdemo.split;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.SideOutputDataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * 使用侧输出流实现分流
 */
public class SideOutputDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());

        env.setParallelism(1);

        SingleOutputStreamOperator<WaterSensor> singleOutputStreamOperator = env.socketTextStream("10.211.55.4", 7777)
                .map((MapFunction<String, WaterSensor>) value -> {
                    String[] arr = value.split(",");
                    return new WaterSensor(arr[0], Long.valueOf(arr[1]), Integer.valueOf(arr[2]));
                });


        OutputTag<WaterSensor> s1Tag = new OutputTag<>("s1", Types.POJO(WaterSensor.class));
        OutputTag<WaterSensor> s2Tag = new OutputTag<>("s2", Types.POJO(WaterSensor.class));

        SingleOutputStreamOperator<WaterSensor> process = singleOutputStreamOperator.process(new ProcessFunction<WaterSensor, WaterSensor>() {
            @Override
            public void processElement(WaterSensor value, ProcessFunction<WaterSensor, WaterSensor>.Context ctx, Collector<WaterSensor> out) throws Exception {
                String id = value.getId();
                if("s1".equals(id)){
                    ctx.output(s1Tag, value);
                }else if("s2".equals(id)){
                    ctx.output(s2Tag, value);
                }else{
                    out.collect(value);
                }
            }
        });

        process.print("主流");

        SideOutputDataStream<WaterSensor> s1SideOutput = process.getSideOutput(s1Tag);
        SideOutputDataStream<WaterSensor> s2SideOutput = process.getSideOutput(s2Tag);

        s1SideOutput.print("侧流s1");
        s2SideOutput.print("侧流s2");

        env.execute();
    }
}
