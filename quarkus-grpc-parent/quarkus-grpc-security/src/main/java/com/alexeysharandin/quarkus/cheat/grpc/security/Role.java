package com.alexeysharandin.quarkus.cheat.grpc.security;

public interface Role {
    String ACCESSOR = "grpc";
    static String grpc() {
        return ACCESSOR;
    }
}
