package com.alexeysharandin.quarkus.cheat.grpc.security;

import io.quarkus.security.credential.TokenCredential;
import io.quarkus.security.identity.request.TokenAuthenticationRequest;

public class GrpcAuthenticationRequest extends TokenAuthenticationRequest {
    public GrpcAuthenticationRequest(TokenCredential token) {
        super(token);
    }
}
