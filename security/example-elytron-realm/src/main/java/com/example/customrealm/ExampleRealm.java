
package com.example.customrealm;

import java.security.Principal;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.wildfly.extension.elytron.Configurable;

import org.wildfly.security.auth.SupportLevel;
import org.wildfly.security.auth.realm.CacheableSecurityRealm;
import org.wildfly.security.auth.server.RealmIdentity;
import org.wildfly.security.auth.server.RealmUnavailableException;
import org.wildfly.security.credential.Credential;
import org.wildfly.security.evidence.Evidence;
import org.wildfly.security.evidence.PasswordGuessEvidence;


public class ExampleRealm implements CacheableSecurityRealm, Configurable {
    
    private Map<String, String> users;
    private Map<String, Set<String>> roles;

    
    public ExampleRealm() {
        // nothing
    }
    
    public ExampleRealm(Map<String,String> map) {
        // test
        initialize(map);
    }
    
    @Override
    public void initialize(Map<String, String> map) {
        users = new HashMap<>();
        roles = new HashMap<>();

        //Adding Guest users
        String guest[] = new String[] { "Guest"};
        for (Map.Entry<String, String> entry : map.entrySet()) {
            // user and password
            users.put(entry.getKey(),entry.getValue());
            roles.put(entry.getKey(),new HashSet(Arrays.asList(guest)));
        }

        // Adding other users
        String array[] = new String[] { "Admin", "Guest"};
        users.put("admin","admin");
        roles.put("admin",new HashSet(Arrays.asList(array)));

        System.out.println("I've loaded "+users.size()+ " users ");
    }
    
    @Override
    public void registerIdentityChangeListener(Consumer<Principal> cnsmr) {
        // does nothing
    }
    

    @Override
    public SupportLevel getCredentialAcquireSupport(Class<? extends Credential> credentialType, String algorithmName,
            AlgorithmParameterSpec parameterSpec) throws RealmUnavailableException {
        return SupportLevel.UNSUPPORTED;
    }


    @Override
    public SupportLevel getEvidenceVerifySupport(Class<? extends Evidence> evidenceType, String algorithmName)
            throws RealmUnavailableException {
        return PasswordGuessEvidence.class.isAssignableFrom(evidenceType)? SupportLevel.POSSIBLY_SUPPORTED : SupportLevel.UNSUPPORTED;
    }

    @Override
    public RealmIdentity getRealmIdentity(final Principal principal) throws RealmUnavailableException {
        String password = users.get(principal.getName());
        if (password != null) {
            return new ExampleRealmIdentity(principal, password, roles.get(principal.getName()));
        }
        return RealmIdentity.NON_EXISTENT;
    }

    @Override
    public String toString() {
        return "ExampleRealm: " + this.users.keySet().stream().collect(Collectors.toList());
    }
    
}
