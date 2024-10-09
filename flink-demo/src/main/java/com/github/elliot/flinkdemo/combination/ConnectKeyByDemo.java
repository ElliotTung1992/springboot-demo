package com.github.elliot.flinkdemo.combination;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoProcessFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 合并流
 */
public class ConnectKeyByDemo {

    public static void main(String[] args) throws Exception {
        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        // 设置并行度
        env.setParallelism(3);
        // 构建数据
        DataStreamSource<Tuple2<Integer, String>> dataStreamSource1 =
                env.fromElements(Tuple2.of(1, "Benz"), Tuple2.of(1, "Audi"), Tuple2.of(2, "Rolls-Royce"), Tuple2.of(3, "Ferrari"));
        DataStreamSource<Tuple3<Integer, String, Integer>> dataStreamSource2 =
                env.fromElements(Tuple3.of(1, "Germany", 11), Tuple3.of(2, "British", 22), Tuple3.of(2, "England", 222), Tuple3.of(3, "Italy", 333));
        // 合并流
        ConnectedStreams<Tuple2<Integer, String>, Tuple3<Integer, String, Integer>> connectedStreams = dataStreamSource1.connect(dataStreamSource2);
        // 多并行度下对流进行keyBy处理
        ConnectedStreams<Tuple2<Integer, String>, Tuple3<Integer, String, Integer>> connectedStreams1 = connectedStreams.keyBy(k1 -> k1.f0, k2 -> k2.f0);
        // 操作流
        SingleOutputStreamOperator<String> streamOperator = connectedStreams1.process(new CoProcessFunction<Tuple2<Integer, String>, Tuple3<Integer, String, Integer>, String>() {

            Map<Integer, List<Tuple2<Integer, String>>> map1 = new HashMap<>();
            Map<Integer, List<Tuple3<Integer, String, Integer>>> map2 = new HashMap<>();

            @Override
            public void processElement1(Tuple2<Integer, String> value, CoProcessFunction<Tuple2<Integer, String>, Tuple3<Integer, String, Integer>, String>.Context ctx, Collector<String> out) throws Exception {
                if(map1.containsKey(value.f0)){
                    map1.get(value.f0).add(value);
                }else{
                    List<Tuple2<Integer, String>> list = new ArrayList<>();
                    list.add(value);
                    map1.put(value.f0, list);
                }

                if(map2.containsKey(value.f0)){
                    for (Tuple3<Integer, String, Integer> item : map2.get(value.f0)) {
                        out.collect("two:" + value.toString() + "three:" + item.toString());
                    }
                }
            }

            @Override
            public void processElement2(Tuple3<Integer, String, Integer> value, CoProcessFunction<Tuple2<Integer, String>, Tuple3<Integer, String, Integer>, String>.Context ctx, Collector<String> out) throws Exception {
                if(map2.containsKey(value.f0)){
                    map2.get(value.f0).add(value);
                }else{
                    List<Tuple3<Integer, String, Integer>> list = new ArrayList<>();
                    list.add(value);
                    map2.put(value.f0, list);
                }

                if(map1.containsKey(value.f0)){
                    for (Tuple2<Integer, String> item : map1.get(value.f0)) {
                        out.collect("two:" + item.toString() + "three:" + value);
                    }
                }
            }
        });
        // 打印流
        streamOperator.print();
        // 执行
        env.execute();
    }
}
