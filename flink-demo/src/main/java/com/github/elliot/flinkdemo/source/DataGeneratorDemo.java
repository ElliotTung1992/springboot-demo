package com.github.elliot.flinkdemo.source;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.source.util.ratelimit.RateLimiterStrategy;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.connector.datagen.source.GeneratorFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;


/**
 * DataGeneratorDemo
 */
public class DataGeneratorDemo {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(3);

        DataGeneratorSource<String> source = new DataGeneratorSource<>(new GeneratorFunction<Long, String>() {

            @Override
            public String map(Long aLong) throws Exception {
                return "Number:" + aLong;
            }
        }, 100, RateLimiterStrategy.perSecond(1.0), Types.STRING);

        env.fromSource(source, WatermarkStrategy.noWatermarks(), "data-generator")
                        .print();

        env.execute();
    }
}
