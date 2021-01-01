package com.github.dge1992.trance.brave;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.*;
import zipkin2.codec.SpanBytesEncoder;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 董感恩
 * @date 2020-09-03 10:51
 * @desc joinSpan测试
 */
public class BraveDemo6 {

    public static void main(String[] args) {

        //设置发送的方式，以及设置发送的位置
        Sender sender = OkHttpSender.create("http://localhost:9411/api/v2/spans");
        //创建异步报告器
        AsyncReporter asyncReporter = AsyncReporter.builder(sender)
                .closeTimeout(500, TimeUnit.MILLISECONDS)
                .build(SpanBytesEncoder.JSON_V2);
        //创建Tracing
        Tracing tracing = Tracing.newBuilder()
                .localServiceName("brave-demo")  // 设置节点名称
                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
                .currentTraceContext(ThreadLocalCurrentTraceContext.newBuilder()
                        .addScopeDecorator(MDCScopeDecorator.create())
                        .build()
                )
                .spanReporter(asyncReporter).build();

        //创建Tracer
        Tracer tracer = tracing.tracer();

        //创建主节点
        Span root = tracer.nextSpan();
        root.name("root");
        root.start();

        //设置节点范围
        Tracer.SpanInScope est = tracer.withSpanInScope(root);

        TraceContext.Extractor<Map<String, String>> extractor = tracing.propagation().extractor(GETTER);
        TraceContext.Injector<Map<String, String>> injector = tracing.propagation().injector(SETTER);

        //获取当前TraceContext
        Map<String, String> map = new HashMap<>();
        TraceContext traceContext = tracing.currentTraceContext().get();
        System.out.println(traceContext);

        //放
        injector.inject(traceContext, map);

        root.tag("name", "root");
        root.kind(Span.Kind.CLIENT);
        doSomethingExpensive();

        //取
        TraceContextOrSamplingFlags extract = extractor.extract(map);
        TraceContext context = extract.context();
        System.out.println(context);

        Span span = returnSpan(tracer, extract);
        span.tag("age", "12");
        span.name("next");
        span.kind(Span.Kind.SERVER);
        span.start();
        doSomethingExpensive();
        span.finish();

        doSomethingExpensive();
        root.finish();

        sleep(2000);
    }

    public static Span returnSpan(Tracer tracer, TraceContextOrSamplingFlags extracted){
        Span span = extracted.context() != null
                ? tracer.joinSpan(extracted.context())
                : tracer.nextSpan(extracted);
        return span;
    }

    private static void doSomethingExpensive() {
        sleep(500);
    }

    private static void commit() {
        sleep(500);
    }

    private static void prepare() {
        sleep(500);
    }

    private static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static final Propagation.Getter<Map<String, String>, String> GETTER =
            new Propagation.Getter<Map<String, String>, String>() {
                @Override
                public String get(Map<String, String> carrier, String key) {
                    return carrier.get(key);
                }

                @Override
                public String toString() {
                    return "Map::get";
                }
            };

    static final Propagation.Setter<Map<String, String>, String> SETTER =
            new Propagation.Setter<Map<String, String>, String>() {
                @Override
                public void put(Map<String, String> carrier, String key, String value) {
                    carrier.put(key, value);
                }

                @Override
                public String toString() {
                    return "Map::set";
                }
            };
}
