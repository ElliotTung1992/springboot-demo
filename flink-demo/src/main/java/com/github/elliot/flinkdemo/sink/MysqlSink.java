package com.github.elliot.flinkdemo.sink;

import com.github.elliot.flinkdemo.bean.WaterSensor;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.connector.jdbc.JdbcStatementBuilder;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.util.Collector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlSink {

    public static void main(String[] args) throws Exception {
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        DataStreamSource<String> source = env.socketTextStream("10.211.55.4", 7777);
        SingleOutputStreamOperator<WaterSensor> streamOperator = source.flatMap(new FlatMapFunction<String, WaterSensor>() {
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
        SinkFunction<WaterSensor> sink = JdbcSink.sink("INSERT INTO `elliot`.`t_order` (`id`, `order_no`, `commodity_code`) VALUES (?, ?, ?)",
                new JdbcStatementBuilder<WaterSensor>() {
                    @Override
                    public void accept(PreparedStatement preparedStatement, WaterSensor sensor) throws SQLException {
                        preparedStatement.setInt(1, Integer.valueOf(sensor.getId()));
                        preparedStatement.setString(2, sensor.getSt().toString());
                        preparedStatement.setString(3, sensor.getVc().toString());
                    }
                },
                JdbcExecutionOptions.builder()
                        .withMaxRetries(3)
                        .withBatchSize(100)
                        .withBatchIntervalMs(3000)
                        .build(),
                new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                        .withUrl("jdbc:mysql://localhost:3306/elliot?useUnicode=true&characterEncoding=utf8")
                        .withUsername("root")
                        .withPassword("dge_1992@163.com")
                        .withConnectionCheckTimeoutSeconds(60)
                        .build());
        streamOperator.addSink(sink);
        env.execute();
    }
}
