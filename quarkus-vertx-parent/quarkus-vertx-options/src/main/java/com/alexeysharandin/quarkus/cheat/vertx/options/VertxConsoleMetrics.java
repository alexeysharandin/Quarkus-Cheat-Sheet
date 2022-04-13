package com.alexeysharandin.quarkus.cheat.vertx.options;

import io.vertx.core.Vertx;
import io.vertx.core.datagram.DatagramSocketOptions;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.*;
import io.vertx.core.spi.observability.HttpRequest;
import io.vertx.core.spi.observability.HttpResponse;
import org.jboss.logging.Logger;

public class VertxConsoleMetrics implements VertxMetrics {
    private static final Logger LOGGER = Logger.getLogger(VertxConsoleMetrics.class);


    @Override
    public EventBusMetrics<?> createEventBusMetrics() {
        LOGGER.info("createEventBusMetrics");
        return VertxMetrics.super.createEventBusMetrics();
    }

    @Override
    public HttpServerMetrics<?, ?, ?> createHttpServerMetrics(HttpServerOptions options, SocketAddress localAddress) {
        LOGGER.info("createHttpServerMetrics:options = " + options + ", localAddress = " + localAddress);
        return new HttpServerMetrics<Object, Object, Object>() {
            @Override
            public Object requestBegin(Object socketMetric, HttpRequest request) {
                LOGGER.info("socketMetric = " + socketMetric + ", request = " + request);
                return HttpServerMetrics.super.requestBegin(socketMetric, request);
            }

            @Override
            public void requestEnd(Object requestMetric, HttpRequest request, long bytesRead) {
                LOGGER.info("requestMetric = " + requestMetric + ", request = " + request + ", bytesRead = " + bytesRead);
                HttpServerMetrics.super.requestEnd(requestMetric, request, bytesRead);
            }

            @Override
            public void requestReset(Object requestMetric) {
                LOGGER.info("requestMetric = " + requestMetric);
                HttpServerMetrics.super.requestReset(requestMetric);
            }

            @Override
            public void responseBegin(Object requestMetric, HttpResponse response) {
                LOGGER.info("requestMetric = " + requestMetric + ", response = " + response);
                HttpServerMetrics.super.responseBegin(requestMetric, response);
            }

            @Override
            public Object responsePushed(Object socketMetric, HttpMethod method, String uri, HttpResponse response) {
                LOGGER.info("socketMetric = " + socketMetric + ", method = " + method + ", uri = " + uri + ", response = " + response);
                return HttpServerMetrics.super.responsePushed(socketMetric, method, uri, response);
            }

            @Override
            public void responseEnd(Object requestMetric, HttpResponse response, long bytesWritten) {
                LOGGER.info("requestMetric = " + requestMetric + ", response = " + response + ", bytesWritten = " + bytesWritten);
                HttpServerMetrics.super.responseEnd(requestMetric, response, bytesWritten);
            }

            @Override
            public Object connected(Object socketMetric, Object requestMetric, ServerWebSocket serverWebSocket) {
                LOGGER.info("socketMetric = " + socketMetric + ", requestMetric = " + requestMetric + ", serverWebSocket = " + serverWebSocket);
                return HttpServerMetrics.super.connected(socketMetric, requestMetric, serverWebSocket);
            }

            @Override
            public void disconnected(Object serverWebSocketMetric) {
                LOGGER.info("serverWebSocketMetric = " + serverWebSocketMetric);
                HttpServerMetrics.super.disconnected(serverWebSocketMetric);
            }

            @Override
            public void requestRouted(Object requestMetric, String route) {
                LOGGER.info("requestMetric = " + requestMetric + ", route = " + route);
                HttpServerMetrics.super.requestRouted(requestMetric, route);
            }
        };
    }

    @Override
    public ClientMetrics<?, ?, ?, ?> createClientMetrics(SocketAddress remoteAddress, String type, String namespace) {
        LOGGER.info("createClientMetrics:remoteAddress = " + remoteAddress + ", type = " + type + ", namespace = " + namespace);
        return VertxMetrics.super.createClientMetrics(remoteAddress, type, namespace);
    }

    @Override
    public HttpClientMetrics<?, ?, ?, ?> createHttpClientMetrics(HttpClientOptions options) {
        LOGGER.info("createHttpClientMetrics:options = " + options);
        return VertxMetrics.super.createHttpClientMetrics(options);
    }

    @Override
    public TCPMetrics<?> createNetServerMetrics(NetServerOptions options, SocketAddress localAddress) {
        LOGGER.info("createNetServerMetrics:options = " + options + ", localAddress = " + localAddress);
        return VertxMetrics.super.createNetServerMetrics(options, localAddress);
    }

    @Override
    public TCPMetrics<?> createNetClientMetrics(NetClientOptions options) {
        LOGGER.info("createNetClientMetrics:options = " + options);
        return VertxMetrics.super.createNetClientMetrics(options);
    }

    @Override
    public DatagramSocketMetrics createDatagramSocketMetrics(DatagramSocketOptions options) {
        LOGGER.info("createDatagramSocketMetrics:options = " + options);
        return VertxMetrics.super.createDatagramSocketMetrics(options);
    }

    @Override
    public PoolMetrics<?> createPoolMetrics(String poolType, String poolName, int maxPoolSize) {
        LOGGER.info("createPoolMetrics:poolType = " + poolType + ", poolName = " + poolName + ", maxPoolSize = " + maxPoolSize);
        return VertxMetrics.super.createPoolMetrics(poolType, poolName, maxPoolSize);
    }

    @Override
    public void vertxCreated(Vertx vertx) {
        LOGGER.info("vertxCreated:vertx = " + vertx);
        VertxMetrics.super.vertxCreated(vertx);
    }

    @Override
    public boolean isMetricsEnabled() {
        LOGGER.info("isMetricsEnabled");
        return true;
    }

    @Override
    public void close() {
        LOGGER.info("close");
        VertxMetrics.super.close();
    }
}
