# Project Base for Vaadin Flow and Quarkus

This project can be used as a starting point to create your own Vaadin Flow application for Quarkus. It contains all the necessary configuration with some placeholder files to get you started.

Quarkus 3.0+ requires Java 17.

Starter is also available for [gradle](https://github.com/vaadin/base-starter-flow-quarkus/tree/gradle)

## Running the Application

Import the project to the IDE of your choosing as a Maven project. 

Run application using `mvnw` (Windows), or `./mvnw` (Mac & Linux).

Open [http://localhost:8080/](http://localhost:8080/) in browser.

If you want to run your app locally in production mode, call `mvnw package -Pproduction` (Windows), or `./mvnw package -Pproduction` (Mac & Linux)
and then
```
java -jar target/quarkus-app/quarkus-run.jar
```

### Including vaadin-jandex for Pro components
If you are using Pro components such GridPro you need to provide the Jandex index for them as well. 
Although, this can be achieved by adding their names one-by-one in the `application.properties` similar to the following example:
```properties
quarkus.index-dependency.vaadin-grid-pro.group-id=com.vaadin
quarkus.index-dependency.vaadin-grid-pro.artifact-id=vaadin-grid-pro-flow
```
Vaadin recommends using the official Jandex index for the Pro components which is published as part of the platform:
```xml
<dependency>
    <groupId>com.vaadin</groupId>
    <artifactId>vaadin-jandex</artifactId>
</dependency>
```
The above dependency has already added to the `pom.xml` and all you need to do is uncomment it when if needed. 
