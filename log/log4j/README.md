HelloWorld Servlet example
=====================================
Example taken from: http://www.mastertheboss.com/javaee/servlet-30/servlet-hello-world-on-wildfly

This example demonstrates the usage of a Servlet in a Jakarta EE 8 Environment.

###### Deploy
```shell
mvn clean install wildfly:deploy
```
###### Test
```shell
http://localhost:8080/helloworld/hello
```
