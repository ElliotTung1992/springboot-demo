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

import java.util.concurrent.TimeUnit;

/**
 * @author 董感恩
 * @date 2020-09-03 10:51
 * @desc span的作用域
 */
public class BraveDemo4 {

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
        Span first = tracer.nextSpan().name("first").start();
        try(Tracer.SpanInScope spanInScope = tracer.withSpanInScope(first)){
            TraceContext traceContext3 = tracing.currentTraceContext().get();
            System.out.println(traceContext3);
            doSomethingExpensive();

            //创建第二个节点
            Span second = tracer.nextSpan().name("second").start();
            try(Tracer.SpanInScope spanInScope1 = tracer.withSpanInScope(second);){
                TraceContext traceContext2 = tracing.currentTraceContext().get();
                System.out.println(traceContext2);
                doSomethingExpensive();
                second.finish();
                doSomethingExpensive();
                first.finish();
            }

            TraceContext traceContext = tracing.currentTraceContext().get();
            System.out.println(traceContext);
        }


        sleep(1000);



        /*Span root = tracer.nextSpan().name("root").start();//创建跨度 root
        try (Tracer.SpanInScope scope = tracer.withSpanInScope(root)) {
            TraceContext traceContext = tracing.currentTraceContext().get();
            System.out.println(traceContext);

            Span second = tracer.nextSpan().name("second").start();
            try (Tracer.SpanInScope scope2 = tracer.withSpanInScope(second)) {
                TraceContext traceContext2 = tracing.currentTraceContext().get();
                System.out.println(traceContext2);
            }
            second.finish();

            TraceContext traceContext3 = tracing.currentTraceContext().get();
            System.out.println(traceContext3);
        }
        root.finish();

        sleep(1000);*/
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
