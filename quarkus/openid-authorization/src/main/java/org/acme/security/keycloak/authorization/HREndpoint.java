package org.acme.security.keycloak.authorization;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import io.quarkus.security.identity.SecurityIdentity;
import java.util.Set;
import javax.inject.*;

@Path("/api/hr")
// Not needed when the Identity manager controls Authorization
//@RolesAllowed("manager")
public class HREndpoint {
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
