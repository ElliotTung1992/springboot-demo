package com.github.dge1992.fish.java.util.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author dge
 * @version 1.0
 * @date 2021-09-13 14:32
 */
public class BigDecimalCollector implements Collector<BigDecimal, List<BigDecimal>, BigDecimal> {

    private final Function<List<BigDecimal>, BigDecimal> finisher;

    private BigDecimalCollector(Function<List<BigDecimal>, BigDecimal> finisher) {
        this.finisher = finisher;
    }

    @Override
    public Supplier<List<BigDecimal>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<BigDecimal>, BigDecimal> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<BigDecimal>> combiner() {
        return (left, right) -> { left.addAll(right); return left; };
    }

    @Override
    public Function<List<BigDecimal>, BigDecimal> finisher() {
        return this.finisher;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

    public static BigDecimalCollector sum() {
        return new BigDecimalCollector(sumFunction());
    }

    private static Function<List<BigDecimal>, BigDecimal> sumFunction(){
        return (list) -> {
            BigDecimal start = BigDecimal.ZERO;
            for (BigDecimal bigDecimal : list) {
                start = start.add(bigDecimal);
            }
            return start;
        };
    }
}
