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

import org.wildfly.security.evidence.PasswordGuessEvidence;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.security.Principal;

/**
 * The custom CallbackHandler to be used with custom login modules on server.
 */
public class CustomCallbackHandler implements CallbackHandler {

    private Principal principal;
    private char[] evidence;

    public CustomCallbackHandler() {
    }

    /**
     * Sets this handler's state. This method was required to be implemented for server side custom login modules.
     *
     * @param principal the principal being authenticated.
     * @param evidence  the evidence being verified.
     */
    public void setSecurityInfo(final Principal principal, final Object evidence) {
        this.principal = principal;
        if (evidence instanceof PasswordGuessEvidence) {
            this.evidence = ((PasswordGuessEvidence) evidence).getGuess();
        }
    }

    public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
        if (callbacks == null) {
            throw new IllegalArgumentException("The callbacks argument cannot be null");
        }

        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback && principal != null) {
                NameCallback nameCallback = (NameCallback) callback;
                nameCallback.setName(this.principal.getName());
            } else if (callback instanceof PasswordCallback) {
                PasswordCallback passwordCallback = (PasswordCallback) callback;
                passwordCallback.setPassword((this.evidence));
            } else {
                throw new UnsupportedCallbackException(callback, "Unsupported callback");
            }
        }
    }
}
