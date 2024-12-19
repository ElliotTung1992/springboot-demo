package com.github.elliot.flinkdemo.sql;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.annotation.DataTypeHint;
import org.apache.flink.table.annotation.InputGroup;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.functions.ScalarFunction;

import static org.apache.flink.table.api.Expressions.$;
import static org.apache.flink.table.api.Expressions.call;

/**
 * 实现自定义标量函数 - 一对一
 */
public class MyScalarFunction {

    public static void main(String[] args) {
        // create env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // data source
        DataStreamSource<WaterSensor> source = env.fromElements(
                new WaterSensor("s1", 1L, 1),
                new WaterSensor("s2", 2L, 2),
                new WaterSensor("s3", 3L, 3));
        // create table env
        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(env);

        // stream -> table
        Table table = tableEnvironment.fromDataStream(source);
        tableEnvironment.createTemporaryView("sensor", table);

        // register function
        tableEnvironment.createTemporaryFunction("HashCodeScalarFunction", HashCodeScalarFunction.class);

        // sql
        /*tableEnvironment.sqlQuery("select HashCodeScalarFunction(id) from sensor")
                .execute()
                .print();*/

        // table api
        table.select(call("HashCodeScalarFunction", $("id")))
                .execute()
                .print();
    }

    public static class HashCodeScalarFunction extends ScalarFunction {

        public int eval(@DataTypeHint(inputGroup = InputGroup.ANY) Object o){
            return o.hashCode();
        }
    }
}
