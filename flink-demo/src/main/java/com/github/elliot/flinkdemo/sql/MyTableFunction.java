package com.github.elliot.flinkdemo.sql;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.annotation.DataTypeHint;
import org.apache.flink.table.annotation.FunctionHint;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.types.Row;

import static org.apache.flink.table.api.Expressions.$;

/**
 * 表函数
 */
public class MyTableFunction {

    public static void main(String[] args) {
        // create env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // create resource
        DataStreamSource<String> source =
                env.fromElements("hello world", "hello flink", "hello Java Project");
        // create table env
        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(env);
        Table table = tableEnvironment.fromDataStream(source, $("words"));
        tableEnvironment.createTemporaryView("str", table);
        // register function
        tableEnvironment.createTemporaryFunction("SplitFunction", SplitFunction.class);
        // execute sql
        tableEnvironment
                // 交叉链接
                // .sqlQuery("select words, word, length from str, lateral table(SplitFunction(words))")
                // 左链接
                // .sqlQuery("select words, word, length from str left join lateral table(SplitFunction(words)) on true")
                // 左链接 重命名
                .sqlQuery("select words, newWord, newLength from str left join lateral table(SplitFunction(words)) as T(newWord, newLength) on true")
                .execute()
                .print();
    }

    @FunctionHint(output = @DataTypeHint("ROW<word STRING,length INT>"))
    public static class SplitFunction extends TableFunction<Row>{

        public void eval(String str){
            for (String word : str.split(" ")) {
                collect(Row.of(word, word.length()));
            }
        }
    }
}
