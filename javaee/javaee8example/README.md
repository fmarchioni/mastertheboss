javaee8example:
===============================
Author: Francesco Marchioni
Level: Intermediate
Technologies: EJB, CDI, JSF
Summary: Basic example that shows a mix of technologies configured using Eclipse and Eclipse Maven plugin
Target Product: WildFly 14 or higher 


System requirements
-------------------

All you need to build this project is Java 8.0 (Java SDK 1.8) or better, Maven 3.0 or better.

The application this project produces is designed to be run on WildFly 14 or higher.

 
Start WildFly
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

 
Build and Deploy the application
-------------------------


1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean package wildfly:deploy

4. This will deploy `target/javaee8example.war` to the running instance of the server.


Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/javaee8example>.


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy


 
