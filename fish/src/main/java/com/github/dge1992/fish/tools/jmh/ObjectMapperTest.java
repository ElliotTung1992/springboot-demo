package com.github.dge1992.fish.tools.jmh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MINUTES)
@State(Scope.Thread)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MINUTES)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MINUTES)
@Fork(1)
@Threads(10)
public class ObjectMapperTest {

    String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";

    @State(Scope.Benchmark)
    public static class BenchMarkState {
        ObjectMapper objectMapper = new ObjectMapper();
        ThreadLocal<ObjectMapper> ObjectMapperThreadLocal = new InheritableThreadLocal<>();
    }

    @Benchmark
    public Map globalTest(BenchMarkState benchMarkState) throws JsonProcessingException {
        return benchMarkState.objectMapper.readValue(json, Map.class);
    }

    @Benchmark
    public Map globalTestThreadLocal(BenchMarkState benchMarkState) throws JsonProcessingException {
        if(Objects.isNull(benchMarkState.ObjectMapperThreadLocal.get())){
            benchMarkState.ObjectMapperThreadLocal.set(new ObjectMapper());
        }
        return benchMarkState.ObjectMapperThreadLocal.get().readValue(json, Map.class);
    }

    @Benchmark
    public Map localTest(BenchMarkState benchMarkState) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Map.class);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(ObjectMapperTest.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .build();
        new Runner(options)
                .run();
    }
}
