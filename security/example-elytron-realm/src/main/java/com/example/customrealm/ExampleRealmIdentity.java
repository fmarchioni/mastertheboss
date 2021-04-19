
package com.example.customrealm;

import java.security.Principal;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Set;
import org.wildfly.security.auth.SupportLevel;
import org.wildfly.security.auth.server.RealmIdentity;
import org.wildfly.security.auth.server.RealmUnavailableException;
import org.wildfly.security.authz.Attributes;
import org.wildfly.security.authz.AuthorizationIdentity;
import org.wildfly.security.authz.MapAttributes;
import org.wildfly.security.credential.Credential;
import org.wildfly.security.evidence.Evidence;
import org.wildfly.security.evidence.PasswordGuessEvidence;


public class ExampleRealmIdentity implements RealmIdentity {

    private final Principal principal;
    private final String password;
    private final Set<String> roles;
    
    public ExampleRealmIdentity(Principal principal, String password, Set<String> roles) {
        this.principal = principal;
        this.password = password;
        this.roles = roles;
    }
    
    @Override
    public Principal getRealmIdentityPrincipal() {
        return principal;
    }

    @Override
    public SupportLevel getCredentialAcquireSupport(Class<? extends Credential> credentialType,
            String algorithmName, AlgorithmParameterSpec parameterSpec) throws RealmUnavailableException {
        // do not support credential acquire
        return SupportLevel.UNSUPPORTED;
    }

    @Override
    public <C extends Credential> C getCredential(Class<C> credentialType) throws RealmUnavailableException {
        // do not return credentials
        return null;
    }

    @Override
    public SupportLevel getEvidenceVerifySupport(Class<? extends Evidence> evidenceType, String algorithmName)
            throws RealmUnavailableException {
        return PasswordGuessEvidence.class.isAssignableFrom(evidenceType)? SupportLevel.SUPPORTED : SupportLevel.UNSUPPORTED;
    }

    @Override
    public boolean verifyEvidence(Evidence evidence) throws RealmUnavailableException {
        if (evidence instanceof PasswordGuessEvidence) {
            PasswordGuessEvidence guess = (PasswordGuessEvidence) evidence;
            return Arrays.equals(password.toCharArray(), guess.getGuess());
        }
        return false;
    }

    @Override
    public boolean exists() throws RealmUnavailableException {
        return true;
    }
    
    @Override
    public Attributes getAttributes() throws RealmUnavailableException {
        if (roles == null || roles.isEmpty()) {
            return Attributes.EMPTY;
        }
        MapAttributes map = new MapAttributes();
        map.addAll("Roles", roles);
        return map;
    }
    
    @Override
    public AuthorizationIdentity getAuthorizationIdentity() throws RealmUnavailableException {
        return AuthorizationIdentity.basicIdentity(getAttributes());
    }
}
