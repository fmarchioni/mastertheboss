# Keycloak WildFly bootable jar example

Build a bootable JAR containing an application secured with Keycloak.
In order to enable keycloak, you must use the Galleon layer `keycloak-client-oidc` that brings in the 
OIDC keycloak subsystem. This subsystem is required for WildFly to secure deployments using keycloak.


Initial Steps
=======

```
docker run --rm     --name keycloak    -e KEYCLOAK_USER=admin    -e KEYCLOAK_PASSWORD=admin     -p 8180:8180    -it quay.io/keycloak/keycloak   \
 -b 0.0.0.0    -Djboss.http.port=8180
 ```

```
docker cp script.sh keycloak:/opt/jboss/keycloak/bin
docker exec -it keycloak chmod 755 /opt/jboss/keycloak/bin/script.sh
docker exec -it keycloak /opt/jboss/keycloak/bin/script.sh
```

Build and run
========

* To build: `mvn package`
* To run: `mvn wildfly-jar:run`
* Access the application: `http://127.0.0.1:8080/simple-webapp`
* Access the secured servlet.
* Log-in using the `demo` user, `demo` password (that you created in the initial steps).
* You should see a page containing the Principal ID.
