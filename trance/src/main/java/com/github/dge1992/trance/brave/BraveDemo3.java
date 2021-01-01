package com.github.dge1992.trance.brave;

import brave.ScopedSpan;
import brave.Span;
import brave.Tracer;
import brave.Tracing;
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import brave.propagation.ThreadLocalCurrentTraceContext;
import zipkin2.codec.SpanBytesEncoder;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import java.util.concurrent.TimeUnit;

/**
 * @author 董感恩
 * @date 2020-09-03 10:51
 * @desc
 */
public class BraveDemo3 {

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

        ScopedSpan parentSpan = tracer.startScopedSpan("parentSpan");
        Span span =  tracer.currentSpan();
        span.tag("key", "firstBiz");

        tracer.startScopedSpanWithParent("childSpan", tracer.currentSpan().context());
        Span childSpan =  tracer.currentSpan();
        childSpan.tag("key", "secondBiz");
        childSpan.finish();

        System.out.println("end tracing,id:" + childSpan.context().traceIdString());
        span.finish();

        sleep(2000);
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
}
