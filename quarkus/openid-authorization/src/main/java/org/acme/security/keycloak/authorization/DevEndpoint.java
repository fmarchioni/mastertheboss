package org.acme.security.keycloak.authorization;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import io.quarkus.security.identity.SecurityIdentity;

import java.util.Set;

@Path("/api/dev")
public class DevEndpoint {

    @Inject
    SecurityIdentity securityContext;

    @GET
    public String access() {
        String userName = securityContext.getPrincipal().getName();
        StringBuffer buffer = new StringBuffer();
        buffer.append("Username: "+userName);
        buffer.append("\nRoles: ");
        Set<String> roles = securityContext.getRoles();

        roles.forEach(role -> buffer.append(role).append("\n"));
        return buffer.toString();
    }
}
