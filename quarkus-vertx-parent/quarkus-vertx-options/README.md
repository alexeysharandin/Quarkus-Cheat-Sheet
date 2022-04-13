# Quarkus-Cheat-Sheet - How to Change Vertx configuration in Quarkus

## Classes

``` java
QuarkusVertxOptionsCustomizer
```
override
``` java
io.vertx.core.VertxOptions
```

Classes:
``` java
VertxConsoleTracer
VertxConsoleMetrics
```


Sample implementations of 
``` java
io.vertx.core.spi.tracing.VertxTracer
io.vertx.core.spi.metrics.VertxMetrics
```

## Build

``` java
mvn package
```
or
``` 
mvn package -Pnative
```
