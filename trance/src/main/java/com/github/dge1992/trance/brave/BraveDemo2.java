package com.github.dge1992.trance.brave;

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
 * @desc 作用域
 */
public class BraveDemo2 {

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

        //创建span
        Span root = tracer.nextSpan().name("root").start();//创建跨度 root
        Tracer.SpanInScope scope = null;
        try {
            scope = tracer.withSpanInScope(root);
            //设置 root 跨度的作用域
            //开始新的跨度 s1。此处使用 currentTraceContext().get() 获取到当前作用域中的 TraceContext
            //TraceContext 中包含着链路中的关键信息，如 TraceId, parentId, spanId 等
            Span s1 = tracer.newChild(tracing.currentTraceContext().get()).name("s1").start();
            System.out.println("被跟踪的业务代码...");
            s1.finish();//结束跨度 s1
        } catch (Exception e) {
            root.error(e);//报错处理
        } finally {
            scope.close();//结束作用域
        }
        root.finish();//结束跨度 root

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
