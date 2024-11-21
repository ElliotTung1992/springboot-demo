package com.github.elliot.flinkdemo.state;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.List;

/**
 * 记录每个水位出现的个数
 * 状态管理
 */
public class KeyProcessMapStateDemo {

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

        SingleOutputStreamOperator<String> process = streamOperator.keyBy(WaterSensor::getId)
                .process(new KeyedProcessFunction<String, WaterSensor, String>() {

                    MapState<Integer, Integer> vcMapState;

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        vcMapState = getRuntimeContext().getMapState(new MapStateDescriptor<Integer, Integer>("vcMapState", TypeInformation.of(Integer.class), TypeInformation.of(Integer.class)));
                    }

                    @Override
                    public void processElement(WaterSensor waterSensor, KeyedProcessFunction<String, WaterSensor, String>.Context ctx, Collector<String> out) throws Exception {
                        Integer vc = waterSensor.getVc();
                        if (vcMapState.contains(vc)) {
                            Integer count = vcMapState.get(vc);
                            vcMapState.put(vc, ++count);
                        }else {
                            vcMapState.put(vc, 1);
                        }
                        StringBuilder sb = new StringBuilder();
                        for (Integer key : vcMapState.keys()) {
                            sb.append("=========\n");
                            sb.append("CurrentKey:" + waterSensor.getId() + " vc:" + key + " count" + vcMapState.get(key) + "\n");
                            sb.append("=========\n");
                        }
                        out.collect(sb.toString());
                    }
                });

        process.print();
        env.execute();
    }
}
