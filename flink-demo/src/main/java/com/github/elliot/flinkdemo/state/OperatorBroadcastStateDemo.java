package com.github.elliot.flinkdemo.state;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.BroadcastConnectedStream;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.util.Collector;

import java.util.Objects;

/**
 * 算子广播状态
 */
public class OperatorBroadcastStateDemo {

    public static void main(String[] args) throws Exception {
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        env.setParallelism(1);

        // main stream
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

        // broadcast stream
        MapStateDescriptor<String, Integer> mapStateDescriptor =
                new MapStateDescriptor<>("broad-cast", Types.STRING, Types.INT);
        BroadcastStream<String> broadcastStream = env.socketTextStream("10.211.55.4", 8888)
                .broadcast(mapStateDescriptor);

        // connect
        BroadcastConnectedStream<WaterSensor, String> broadcastConnectedStream = streamOperator.connect(broadcastStream);

        // process
        SingleOutputStreamOperator<String> process = broadcastConnectedStream.process(new BroadcastProcessFunction<WaterSensor, String, String>() {
            @Override
            public void processElement(WaterSensor waterSensor, BroadcastProcessFunction<WaterSensor, String, String>.ReadOnlyContext ctx, Collector<String> out) throws Exception {
                ReadOnlyBroadcastState<String, Integer> broadcastState =
                        ctx.getBroadcastState(mapStateDescriptor);
                Integer threshold = broadcastState.get("threshold");
                if (Objects.isNull(threshold)) {
                    threshold = 0;
                }
                if (waterSensor.getVc() >= threshold) {
                    out.collect(waterSensor.getVc() + "值超过threshold:" + threshold);
                }
            }

            @Override
            public void processBroadcastElement(String value, BroadcastProcessFunction<WaterSensor, String, String>.Context ctx, Collector<String> out) throws Exception {
                BroadcastState<String, Integer> broadcastState = ctx.getBroadcastState(mapStateDescriptor);
                broadcastState.put("threshold", Integer.valueOf(value));
            }
        });

        process.print();

        // execute
        env.execute();
    }
}
