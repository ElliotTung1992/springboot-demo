package com.github.elliot.flinkdemo.sql;

import org.apache.flink.api.common.state.AggregatingState;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.functions.AggregateFunction;

import static org.apache.flink.table.api.Expressions.$;

/**
 * 自定义聚合函数 - 多对一
 * 案例: 学生成绩加权平均分
 */
public class MyAggregateFunction {

    public static void main(String[] args) {
        // create env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // create source
        DataStreamSource<Tuple3<String, Integer, Integer>> dataStreamSource = env.fromElements(Tuple3.of("Bruce", 80, 3),
                Tuple3.of("Bruce", 90, 4),
                Tuple3.of("Bruce", 95, 4),
                Tuple3.of("Elliot", 80, 3),
                Tuple3.of("Elliot", 85, 4),
                Tuple3.of("Elliot", 90, 4));
        // create table env
        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(env);
        // stream -> table
        Table table = tableEnvironment.fromDataStream(dataStreamSource,
                $("f0").as("name"),
                $("f1").as("source"),
                $("f2").as("weight"));
        // register table
        tableEnvironment.createTemporaryView("sources", table);
        // register custom function
        tableEnvironment.createTemporaryFunction("SourceWeightAvg", SourceWeightAvg.class);
        // invoke - sql
        tableEnvironment.sqlQuery("select name, SourceWeightAvg(source, weight) from sources group by name")
                .execute()
                .print();
        // invoke - tableSql

    }

    /**
     * custom function
     */
    public static class SourceWeightAvg extends AggregateFunction<Double, Tuple2<Integer, Integer>> {

        @Override
        public Double getValue(Tuple2<Integer, Integer> accumulator) {
            return accumulator.f0 * 1D / accumulator.f1;
        }

        @Override
        public Tuple2<Integer, Integer> createAccumulator() {
            return Tuple2.of(0, 0);
        }

        public void accumulate(Tuple2<Integer, Integer> acc, Integer source, Integer weight){
            acc.f0 += source * weight;
            acc.f1 += weight;
        }
    }
}
