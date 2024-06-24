package com.github.elliot.flinkdemo.source;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * 从文件读取数据
 */
public class FileSourceDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        FileSource<String> fileSource = FileSource.forRecordStreamFormat(new TextLineInputFormat(),
                        new Path("/Users/ganendong/Documents/workspace/springboot-demo/flink-demo/data/word_count.txt"))
                .build();

        env.fromSource(fileSource, WatermarkStrategy.noWatermarks(), "fileSource")
                .print();

        env.execute();
    }
}
