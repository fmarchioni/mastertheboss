Primefaces Example
===============================
Author: Francesco Marchioni  
Level: Beginner  
Technologies: JSF  
Summary: This project demonstrates the usage of Primefaces.  
Target Product: WildFly  
Read more: https://www.mastertheboss.com/web/primefaces/primefaces-tutorial/

 
Build and Deploy the Quickstart
-------------------------

1. Make sure you have started the WildFly server  
2. Open a command prompt and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean install wildfly:deploy

4. This will deploy `target/primefaces.war` to the running instance of the server.




Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/primefaces-demo>. 


Using WildFly bootable JAR
--------------------

Please note that you can run your application directly with WildFly Bootable Jar technology by running:

`mvn clean install wildfly-jar:run`

And then access it with:

http://localhost:8080



