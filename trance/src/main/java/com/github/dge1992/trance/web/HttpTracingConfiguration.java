package com.github.dge1992.trance.web;

import brave.Tracing;
import brave.context.slf4j.MDCScopeDecorator;
import brave.http.HttpTracing;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import brave.propagation.ThreadLocalCurrentTraceContext;
import brave.servlet.TracingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.Span;
import zipkin2.codec.SpanBytesEncoder;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

import javax.servlet.Filter;
import java.util.concurrent.TimeUnit;

/**
 * @author 董感恩
 * @date 2020-09-07 10:57
 * @desc
 */
@Configuration
public class HttpTracingConfiguration {

    private String url = "http://localhost:9411/api/v2/spans";

    @Bean
    Sender sender() {
        return OkHttpSender.create(url);
    }

    @Bean
    AsyncReporter<Span> spanReporter(Sender sender) {
        return AsyncReporter.builder(sender)
                .closeTimeout(500, TimeUnit.MILLISECONDS)
                .build(SpanBytesEncoder.JSON_V2);
    }

    @Bean
    Tracing tracing(AsyncReporter asyncReporter) {
        Tracing tracing = Tracing.newBuilder()
                .localServiceName("brave-demo")  // 设置节点名称
                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
                .currentTraceContext(ThreadLocalCurrentTraceContext.newBuilder()
                        .addScopeDecorator(MDCScopeDecorator.create())
                        .build()
                )
                .spanReporter(asyncReporter).build();
        return tracing;
    }

    @Bean
    HttpTracing httpTracing(Tracing tracing) {
        return HttpTracing.create(tracing);
    }

    @Bean
    Filter tracingFilter(HttpTracing httpTracing) {
        return TracingFilter.create(httpTracing);
    }
}
