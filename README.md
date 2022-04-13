# Quarkus-Cheat-Sheet

Created for personal usage.

This repo contains some code examples and cheats what not documented in https://quarkus.io 

If you have a question about some functions what not included to this list - please raise a ticket. It's can help to improve this FAQ.
Information will be added (if it's possible) 

If you have some cheats to add -> please add to this repo

## Table of Context

### Vert.x
1. [How to change Vert.x configuration (options)](./quarkus-vertx-parent/quarkus-vertx-options). For example: trace all requests(http/sql) or add metrix support or something else.\
Description: *Some Vert.x configuration not available over application.config. This example show how to set/update this configuration*

### GRPC
1. [How to add GRPC security in Quarkus application](./quarkus-grpc-parent/quarkus-grpc-security).\
   Description: *GRPC security implemented but not documented This example show how to work with it*

### Native
1. [How to build lightweight Alpine container with Quarkus application](./quarkus-native-image/quarkus-lightweight-container)\
   Description: *GraalVM native-image using glibc but Alpine using musl*