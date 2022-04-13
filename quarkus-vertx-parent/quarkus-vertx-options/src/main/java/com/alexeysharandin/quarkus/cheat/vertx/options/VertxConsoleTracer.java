package com.alexeysharandin.quarkus.cheat.vertx.options;

import io.vertx.core.Context;
import io.vertx.core.spi.tracing.SpanKind;
import io.vertx.core.spi.tracing.TagExtractor;
import io.vertx.core.spi.tracing.VertxTracer;
import io.vertx.core.tracing.TracingPolicy;
import org.jboss.logging.Logger;

import java.util.Map;
import java.util.function.BiConsumer;

public class VertxConsoleTracer implements VertxTracer<Object, Object> {
    private static final Logger LOGGER = Logger.getLogger(VertxConsoleTracer.class);


    @Override
    public <R> Object receiveRequest(Context context, SpanKind kind, TracingPolicy policy, R request, String operation, Iterable<Map.Entry<String, String>> headers, TagExtractor<R> tagExtractor) {
        LOGGER.info("receiveRequest:context = " + context + ", kind = " + kind + ", policy = " + policy + ", request = " + request + ", operation = " + operation + ", headers = " + headers + ", tagExtractor = " + tagExtractor);
        return VertxTracer.super.receiveRequest(context, kind, policy, request, operation, headers, tagExtractor);
    }

    @Override
    public <R> void sendResponse(Context context, R response, Object payload, Throwable failure, TagExtractor<R> tagExtractor) {
        LOGGER.info("sendResponse:context = " + context + ", response = " + response + ", payload = " + payload + ", failure = " + failure + ", tagExtractor = " + tagExtractor);
        VertxTracer.super.sendResponse(context, response, payload, failure, tagExtractor);
    }

    @Override
    public <R> Object sendRequest(Context context, SpanKind kind, TracingPolicy policy, R request, String operation, BiConsumer<String, String> headers, TagExtractor<R> tagExtractor) {
        LOGGER.info("sendRequest:context = " + context + ", kind = " + kind + ", policy = " + policy + ", request = " + request + ", operation = " + operation + ", headers = " + headers + ", tagExtractor = " + tagExtractor);
        return VertxTracer.super.sendRequest(context, kind, policy, request, operation, headers, tagExtractor);
    }

    @Override
    public <R> void receiveResponse(Context context, R response, Object payload, Throwable failure, TagExtractor<R> tagExtractor) {
        LOGGER.info("receiveResponse:context = " + context + ", response = " + response + ", payload = " + payload + ", failure = " + failure + ", tagExtractor = " + tagExtractor);
        VertxTracer.super.receiveResponse(context, response, payload, failure, tagExtractor);
    }

    @Override
    public void close() {
        LOGGER.info("close");
        VertxTracer.super.close();
    }
}
