package com.github.elliot.flinkdemo.state;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * 算子状态 - 列表状态
 */
public class OperatorListStateDemo {

    public static void main(String[] args) throws Exception {
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        env.setParallelism(1);

        SingleOutputStreamOperator<Integer> streamOperator = env.socketTextStream("10.211.55.4", 7777)
                .map(new MyMapFunction());
        streamOperator.print();
        env.execute();
    }

    static class MyMapFunction implements MapFunction<String, Integer>, CheckpointedFunction {

        private Integer count = 0;

        private ListState<Integer> listState;

        @Override
        public Integer map(String value) throws Exception {
            return ++count;
        }

        @Override
        public void snapshotState(FunctionSnapshotContext context) throws Exception {
            System.out.println("snapshotState");
            listState.clear();
            listState.add(count);
        }

        @Override
        public void initializeState(FunctionInitializationContext context) throws Exception {
            System.out.println("initializeState");
            listState = context.getOperatorStateStore()
                    .getListState(new ListStateDescriptor<Integer>("listState", Types.INT));
            for (Integer integer : listState.get()) {
                count += integer;
            }
        }
    }
}
