This project was created from the archetype "wildfly-jakartaee-webapp-archetype".

To deploy it:
Run the maven goals "install wildfly:deploy"

To undeploy it:
Run the maven goals "wildfly:undeploy"

==========================

DataSource:
This sample includes a "persistence.xml" file in "src/main/resources/META-INF". This file defines
a persistence unit "sample-jakartaee-webapp-archetypePersistenceUnit" which uses the JakartaEE default database.

In production environment, you should define a database in WildFly config and point to this database
in "persistence.xml".

If you don't use entity beans, you can delete "persistence.xml".
==========================

JSF:
The web application is prepared for JSF 2.3 by bundling an empty "faces-config.xml" in "src/main/webapp/WEB-INF".
In case you don't want to use JSF, simply delete this file and "src/main/webapp/beans.xml" and "src/main/java/com/masterheboss/Jsf23Activator.java"
==========================

Testing:
This sample is prepared for running unit tests with the Arquillian framework.

The configuration can be found in "sample-jakartaee-webapp-archetype/pom.xml":

Three profiles are defined:
-"default": no integration tests are executed.
-"arq-remote": you have to start a WildFly server on your machine. The tests are executed by deploying 
 the application to this server.
 Here the "maven-failsafe-plugin" is enabled so that integration tests can be run.
 Run maven with these arguments: "clean verify -Parq-remote"
-"arq-managed": this requires the environment variable "JBOSS_HOME" to be set: 
 The server found in this path is started and the tests are executed by deploying the application to this server.
 Instead of using this environment variable, you can also define the path in "arquillian.xml".
 Here the "maven-failsafe-plugin" is enabled so that integration tests can be run.
 Run maven with these arguments: "clean verify -Parq-managed"

The Arquillian test runner is configured with the file "src/test/resources/arquillian.xml" 
(duplicated in EJB and WEB project, depending where your tests are placed).
The profile "arq-remote" uses the container qualifier "remote" in this file.
The profile "arq-managed" uses the container qualifier "managed" in this file.

The project contains an integration test "SampleIT" which shows how to create the deployable WAR file using the ShrinkWrap API.
You can delete this test file if no tests are necessary.

Why integration tests instead of the "maven-surefire-plugin" testrunner?
The Arquillian test runner deploys the WAR file to the WildFly server and thus you have to build it yourself with the ShrinkWrap API.
The goal "verify" (which triggers the maven-surefire-plugin) is executed later in the maven build lifecyle than the "test" goal so that the target 
artifact ("sample-jakartaee-webapp-archetype.war") is already built. You can build
the final WAR by including those files. The "maven-surefire-plugin" is executed before the WAR file
are created, so this WAR files would have to be built in the "@Deployment" method, too. 
