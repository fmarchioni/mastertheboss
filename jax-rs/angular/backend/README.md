# Jakarta EE Rest Application

This is a sample Jakarta EE project which exposes a set of REST API through the following endpoint:

```
http://localhost:8080/jaxrs-demo/customer
```

You can build it and deploy it on WildFly as follows:

```
mvn install wildfly:deploy
```

You also need to enable CORS on WildFly to allow the interaction with Angular.
Read more here: https://www.mastertheboss.com/web/jboss-web-server/how-to-configure-cors-on-wildfly/
