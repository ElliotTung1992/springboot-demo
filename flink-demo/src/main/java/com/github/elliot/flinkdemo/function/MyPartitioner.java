package com.github.elliot.flinkdemo.function;

import org.apache.flink.api.common.functions.Partitioner;

public class MyPartitioner implements Partitioner<Integer> {

    @Override
    public int partition(Integer key, int numPartitions) {
        return key % numPartitions;
    }
}
