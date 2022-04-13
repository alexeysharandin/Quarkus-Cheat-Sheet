package com.alexeysharandin.quarkus.cheat.grpc.security;

import io.grpc.Metadata;
import io.quarkus.grpc.auth.GrpcSecurityMechanism;
import io.quarkus.security.credential.TokenCredential;
import io.quarkus.security.identity.request.AuthenticationRequest;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GrpcSecurityMechanismConfiguration implements GrpcSecurityMechanism {
    @Override
    public boolean handles(Metadata metadata) {
        String token = getToken(metadata);
        return token != null;
    }

    private String getToken(Metadata metadata) {
        return metadata.get(Metadata.Key.of("token", Metadata.ASCII_STRING_MARSHALLER));
    }

    @Override
    public AuthenticationRequest createAuthenticationRequest(Metadata metadata) {
        String token = getToken(metadata);
        return new GrpcAuthenticationRequest(new TokenCredential(token, "grpc"));
    }
}
