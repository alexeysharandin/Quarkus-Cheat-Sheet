package com.alexeysharandin.quarkus.cheat.vertx.options;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.vertx.core.deployment.VertxOptionsConsumerBuildItem;
import io.vertx.core.VertxOptions;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.tracing.TracingOptions;
import org.jboss.logging.Logger;

import javax.ws.rs.ext.Provider;
import java.util.function.Consumer;

@SuppressWarnings("unused")
@Provider
public class QuarkusVertxOptionsCustomizer {
    private static final Logger LOGGER = Logger.getLogger(QuarkusVertxOptionsCustomizer.class);
    private final static int PRIORITY = 1000;

    @BuildStep
    VertxOptionsConsumerBuildItem addTracer() {
        LOGGER.info("Add VertxOptionsConsumerBuildItem to Quarkus Build");
        return new VertxOptionsConsumerBuildItem(new VertxOptionsConsumer(), PRIORITY);
    }

    private static void applyVertxOptions(VertxOptions vertxOptions) {
        applyTracingOptions(vertxOptions);
        applyMetrixOptions(vertxOptions);
    }

    private static void applyTracingOptions(VertxOptions vertxOptions) {
        TracingOptions tracingOptions = vertxOptions.getTracingOptions();
        if (tracingOptions == null) {
            LOGGER.info("Tracing options not exist. Creating new");
            tracingOptions = new TracingOptions();
        } else {
            LOGGER.info("Tracing options exist: " + tracingOptions + ". Use some logic if needed");
        }
        tracingOptions.setFactory(options -> new VertxConsoleTracer());

        vertxOptions.setTracingOptions(tracingOptions);
    }

    private static void applyMetrixOptions(VertxOptions vertxOptions) {
        MetricsOptions metricsOptions = vertxOptions.getMetricsOptions();
        if (metricsOptions == null) {
            LOGGER.info("Metrics options not exist. Creating new");
            metricsOptions = new MetricsOptions();
        } else {
            LOGGER.info("Metrics options exist: " + metricsOptions + ". Use some logic if needed");
            metricsOptions.setEnabled(true);
        }
        metricsOptions.setFactory(options -> new VertxConsoleMetrics());
        vertxOptions.setMetricsOptions(metricsOptions);
    }

    public static class VertxOptionsConsumer implements Consumer<VertxOptions> {
        @Override
        public void accept(VertxOptions vertxOptions) {
            applyVertxOptions(vertxOptions);
        }
    }

}
