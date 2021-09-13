Primefaces Chart Example
===============================
Author: Francesco Marchioni  
Level: Beginner  
Technologies: JSF  
Summary: This project demonstrates the usage of Primefaces Charts.  
Target Product: WildFly  

 
Build and Deploy the Quickstart
-------------------------

1. Make sure you have started the WildFly server  
2. Open a command prompt and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean install wildfly:deploy

4. This will deploy `target/primefaces.war` to the running instance of the server.


Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/primefaces-chart>. 


Undeploy the Archive
--------------------

1. Make sure you have started the WildFly server as described above.
2. Open a command prompt and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy



