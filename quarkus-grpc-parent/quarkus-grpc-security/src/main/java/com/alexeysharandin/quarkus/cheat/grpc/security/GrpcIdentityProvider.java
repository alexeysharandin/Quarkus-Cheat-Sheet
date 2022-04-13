package com.alexeysharandin.quarkus.cheat.grpc.security;

import io.quarkus.security.credential.Credential;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.IdentityProvider;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.security.Permission;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@ApplicationScoped
public class GrpcIdentityProvider implements IdentityProvider<GrpcAuthenticationRequest> {
    private static final Logger LOGGER = Logger.getLogger(GrpcIdentityProvider.class);

    private static SecurityIdentity ACCESSOR_ROLE = null;

    String token = "12345678";

    public GrpcIdentityProvider() {
    }

    @Override
    public Class<GrpcAuthenticationRequest> getRequestType() {
        return GrpcAuthenticationRequest.class;
    }

    @Override
    public Uni<SecurityIdentity> authenticate(GrpcAuthenticationRequest request, AuthenticationRequestContext context) {
        LOGGER.info("authenticate request:" + request);
        return Uni.createFrom().item(auth(request, context, token));
    }

    private SecurityIdentity auth(GrpcAuthenticationRequest request, AuthenticationRequestContext context, String token) {
        boolean auth = token.equals(request.getToken().getToken());
        if (auth) {
            return authenticate();
        }
        return anonymous(context);
    }

    private SecurityIdentity anonymous(AuthenticationRequestContext context) {
        return instance;
    }

    private SecurityIdentity authenticate() {
        if (ACCESSOR_ROLE == null) {
            ACCESSOR_ROLE = QuarkusSecurityIdentity
                    .builder()
                    .addRole(Role.grpc())
                    .build();
        }
        return ACCESSOR_ROLE;
    }

    private static final Principal principal = () -> "";

    private static final SecurityIdentity instance = new SecurityIdentity() {
        @Override
        public Principal getPrincipal() {
            return principal;
        }

        @Override
        public boolean isAnonymous() {
            return true;
        }

        @Override
        public Set<String> getRoles() {
            return Collections.emptySet();
        }

        @Override
        public boolean hasRole(String role) {
            return false;
        }

        @Override
        public <T extends Credential> T getCredential(Class<T> credentialType) {
            return null;
        }

        @Override
        public Set<Credential> getCredentials() {
            return Collections.emptySet();
        }

        @Override
        public <T> T getAttribute(String name) {
            return null;
        }

        @Override
        public Map<String, Object> getAttributes() {
            return Collections.emptyMap();
        }

        @Override
        public Uni<Boolean> checkPermission(Permission permission) {
            return Uni.createFrom().item(false);
        }
    };
}
