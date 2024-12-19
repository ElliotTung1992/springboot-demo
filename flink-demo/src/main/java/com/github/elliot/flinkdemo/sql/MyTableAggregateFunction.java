package com.github.elliot.flinkdemo.sql;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.functions.TableAggregateFunction;
import org.apache.flink.util.Collector;

import static org.apache.flink.table.api.Expressions.$;
import static org.apache.flink.table.api.Expressions.call;

/**
 * 自定义表聚合函数 - 多对多
 * 案例 - Top 2
 */
public class MyTableAggregateFunction {

    public static void main(String[] args) {
        // create env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // create resource
        DataStreamSource<Integer> source = env.fromElements(3, 6, 12, 5, 8, 9, 4);
        // create table env
        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(env);
        // stream -> table
        Table table = tableEnvironment.fromDataStream(source, $("source"));

        // register custom function
        tableEnvironment.createTemporaryFunction("Top2", Top2.class);
        // invoke - 只支持tableApi
        table.flatAggregate(call("Top2", $("source")).as("value", "rank"))
                .select($("value"), $("rank"))
                .execute()
                .print();
    }

    /**
     * 自定义表聚合函数 - Top 2
     */
    public static class Top2 extends TableAggregateFunction<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>>{

        @Override
        public Tuple2<Integer, Integer> createAccumulator() {
            return Tuple2.of(0, 0);
        }

        /**
         * 累加处理逻辑
         * @param acc acc
         * @param source source
         */
        public void accumulate(Tuple2<Integer, Integer> acc, Integer source){

            if(source > acc.f1 && source <= acc.f0){
                acc.f1 = source;
            }else if(source > acc.f0) {
                int temp = acc.f0;
                acc.f0 = source;
                acc.f1 = temp;
            }
        }

        /**
         * 数据输出
         * @param acc acc
         * @param out out
         */
        public void emitValue(Tuple2<Integer, Integer> acc, Collector<Tuple2<Integer, Integer>> out){
            if(acc.f0 != 0){
                out.collect(Tuple2.of(acc.f0, 1));
            }
            if(acc.f1 != 0){
                out.collect(Tuple2.of(acc.f1, 2));
            }
        }
    }
}
