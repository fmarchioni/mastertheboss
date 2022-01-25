/*
 * Copyright 2021 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package loginmodules;

import org.wildfly.security.auth.principal.NamePrincipal;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A {@link LoginModule} implementation used in the JAAS security realm tests. It uses a static
 * map of username -> password to determine if a login is successful or not.
 */
public class CustomLoginModule implements LoginModule {

    private final Map<String, char[]> usersMap = new HashMap<String, char[]>();
    private Principal principal;
    private Subject subject;
    private CallbackHandler handler;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.handler = callbackHandler;
        this.usersMap.put("user1", "password1".toCharArray());
        this.usersMap.put("user2", "password2".toCharArray());
    }

    @Override
    public boolean login() throws LoginException {
        // obtain the incoming username and password from the callback handler
        NameCallback nameCallback = new NameCallback("Username");
        PasswordCallback passwordCallback = new PasswordCallback("Password", false);
        Callback[] callbacks = new Callback[]{nameCallback, passwordCallback};
        try {
            this.handler.handle(callbacks);
        } catch (UnsupportedCallbackException | IOException e) {
            throw new LoginException("Error handling callback: " + e.getMessage());
        }

        final String username = nameCallback.getName();
        this.principal = new NamePrincipal(username);
        final char[] password = passwordCallback.getPassword();

        char[] storedPassword = this.usersMap.get(username);
        if (!Arrays.equals(storedPassword, password)) {
            throw new LoginException("Invalid password");
        } else {
            return true;
        }
    }

    @Override
    public boolean commit() throws LoginException {
        if (this.principal.getName().equals("user1") || this.principal.getName().equals("user2")) {
            this.subject.getPrincipals().add(new Roles("Admin"));
            this.subject.getPrincipals().add(new Roles("User"));
            this.subject.getPrincipals().add(new Roles("Guest"));
        }
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        this.subject.getPrincipals().clear();
        return true;
    }

    private static class Roles implements Principal {

        private final String name;

        Roles(final String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
