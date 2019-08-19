package com.example;

import java.io.IOException;
import java.net.InetAddress;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.RealmCallback;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.as.controller.client.helpers.Operations;
import org.jboss.dmr.ModelNode;

public class App {

    public static void main(final String[] args) throws Exception {
        ModelControllerClient client = ModelControllerClient.Factory.create(
                InetAddress.getByName("localhost"), 9990,
                new CallbackHandler() {

                    public void handle(Callback[] callbacks)
                            throws IOException, UnsupportedCallbackException {
                        for (Callback current : callbacks) {
                            if (current instanceof NameCallback) {
                                NameCallback ncb = (NameCallback) current;
                                ncb.setName(args[0]);
                            } else if (current instanceof PasswordCallback) {
                                PasswordCallback pcb = (PasswordCallback) current;
                                pcb.setPassword(args[1].toCharArray());
                            } else if (current instanceof RealmCallback) {
                                RealmCallback rcb = (RealmCallback) current;
                                rcb.setText(rcb.getDefaultText());
                            } else {
                                throw new UnsupportedCallbackException(current);
                            }
                        }
                    }
                });


        final ModelNode address = Operations.createAddress("subsystem", "transactions");
        final ModelNode op = Operations.createWriteAttributeOperation(address, "default-timeout", "120");

        try {
            ModelNode returnVal = client.execute(op);

            System.out.println("Result:");
            System.out.println(returnVal.toJSONString(false));

        } finally {
            client.close();
            System.exit(1);

        }

    }
}