Intra-Server Remote Client EJB lookup 
=====================================

This example builds up a Java EE project which invokes an EJB located in another application.

###### Build and Deploy
```shell
mvn install wildfly:deploy
```

###### Test
```shell
http://localhost:8080/ejb-client-basic/ejbclient?money=200
```
