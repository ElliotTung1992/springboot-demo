package com.github.elliot.flinkdemo.sink;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.source.util.ratelimit.RateLimiterStrategy;
import org.apache.flink.configuration.MemorySize;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.connector.datagen.source.GeneratorFunction;
import org.apache.flink.connector.file.sink.FileSink;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.OutputFileConfig;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.DateTimeBucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;

import java.time.Duration;
import java.time.ZoneId;

/**
 * 数据输出到文件
 */
public class SinkFile {

    public static void main(String[] args) throws Exception {
        // 获取执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 设置并行度
        env.setParallelism(2);

        env.enableCheckpointing(2000, CheckpointingMode.EXACTLY_ONCE);

        // 构建数据源
        DataGeneratorSource<String> dataGeneratorSource = new DataGeneratorSource<>(new GeneratorFunction<Long, String>() {
            @Override
            public String map(Long aLong) throws Exception {
                return "Number:" + aLong;
            }
        }, Long.MAX_VALUE, RateLimiterStrategy.perSecond(100), Types.STRING);
        // 读取数据
        DataStreamSource<String> source =
                env.fromSource(dataGeneratorSource, WatermarkStrategy.noWatermarks(), "dataGeneratorSource");
        // 输出到文件系统
        FileSink<String> fileSink = FileSink.<String>forRowFormat(new Path("/Users/ganendong/Desktop/test"),
                        new SimpleStringEncoder<>("UTF-8"))
                .withOutputFileConfig(OutputFileConfig.builder().withPartPrefix("test").withPartSuffix(".log").build())
                .withBucketAssigner(new DateTimeBucketAssigner<>("yyyy-MM-dd HH", ZoneId.systemDefault()))
                .withRollingPolicy(DefaultRollingPolicy.builder().withRolloverInterval(Duration.ofSeconds(10))
                        .withMaxPartSize(new MemorySize(1024 * 24)).build())
                .build();

        source.sinkTo(fileSink);
        // 执行
        env.execute();
    }
}
