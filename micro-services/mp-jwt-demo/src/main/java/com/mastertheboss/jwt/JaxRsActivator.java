package com.mastertheboss.jwt;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

@ApplicationPath("/rest")
@LoginConfig(authMethod = "MP-JWT", realmName = "quarkus")
@DeclareRoles({"protected"})
public class JaxRsActivator extends Application {
    /* class body intentionally left blank */
}
