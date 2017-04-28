# eap-7-swagger

Demo app for swagger/resteasy integration on EAP 7 (Wildfly)

# Compile

Just run:

``` mvn clena package ```

then:

``` cp target/restserver.war {PATH_TO}/jboss-eap-7.0/standalone/deployments/ ```

# Test

Open browser:

http://localhost:8080/restserver/

You should see swagger-ui

**NOTE:** The application is tailored to work on localhost:8080 it needs some code changes to be automatically exposed on your ip and port.
